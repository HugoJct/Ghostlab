package main.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import main.java.commands.CommandTCP;
import main.java.commands.in.CommandRcvTcpDunno;
import main.java.commands.in.CommandRcvTcpGameInfo;
import main.java.commands.in.CommandRcvTcpJoinNO;
import main.java.commands.in.CommandRcvTcpJoinOK;
import main.java.commands.in.CommandRcvTcpMapSize;
import main.java.commands.in.CommandRcvTcpNbrGames;
import main.java.commands.in.CommandRcvTcpPlayerGame;
import main.java.commands.in.CommandRcvTcpPlayerId;
import main.java.commands.in.CommandRcvTcpUnregisterOK;
import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;


public class ClientTCP extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    private HashMap<String,CommandTCP> commandRcvTcpList = new HashMap<String,CommandTCP>();
    
    public static boolean clientTCPCreated = false;

    public ClientTCP(String ip, int port) {
        try {
            DebugLogger.print(DebugType.COM, "Création de la connection TCP avec le serveur...");
            this.clientSocket = new Socket(ip, port);
            this.out = new PrintWriter(clientSocket.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // remplissage de la liste de commandes recevables
            commandRcvTcpList.put("DUNNO", new CommandRcvTcpDunno(out));
            commandRcvTcpList.put("OGAME", new CommandRcvTcpGameInfo(out));
            commandRcvTcpList.put("REGNO", new CommandRcvTcpJoinNO(out));
            commandRcvTcpList.put("REGOK", new CommandRcvTcpJoinOK(out));
            commandRcvTcpList.put("SIZE!", new CommandRcvTcpMapSize(out));
            commandRcvTcpList.put("GAMES", new CommandRcvTcpNbrGames(out));
            commandRcvTcpList.put("LIST!", new CommandRcvTcpPlayerGame(out));
            commandRcvTcpList.put("PLAYR", new CommandRcvTcpPlayerId(out));
            commandRcvTcpList.put("UNROK", new CommandRcvTcpUnregisterOK(out));

            DebugLogger.print(DebugType.COM, "...succès");

            clientTCPCreated = true;
        } catch (UnknownHostException e) {
            DebugLogger.print(DebugType.ERROR, "...Erreur critique : l'adresse IP de l'hôte ne peut être déterminée");
        } catch (IOException e) {
            System.out.println("...erreur critique : Numero de PORT INDISPONIBLE ou IP INCONNUE");
        }

    }

    @Override
    public void run() {

        DebugLogger.print(DebugType.CONFIRM, "Début de l'écoute TCP");
        
        // tant que le socket est connecté
        while(Client.isConnected) {
            
            try {

                String serverMsg = "";

                int pos = 0;
                int readVal = 0;

                while(pos != 5) {

                    readVal = in.read();

                    // si le socket est déconnecté : arrêter de lire
                    if (readVal == -1) {
                        DebugLogger.print(DebugType.CONFIRM, "Server is closed : disconnected");
                        Client.isConnected = false;
                        break;
                    }

                    serverMsg += (char)readVal;
                    pos++;
                }
                useMessage(serverMsg);
            } catch(IOException e) {
                DebugLogger.print(DebugType.CONFIRM, "Socket closed : disconnected");
            }
        }
    }

    // envoie du message sur la sortie vers le serveur
	public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }

    // parsing de la commande
	public void useMessage(String command) {
        
        String[] args = breakCommand(command);
		
		for(String s : commandRcvTcpList.keySet()) {
			if(s.equals(extractFirst(args[0]))) {
                // appel de la fonction dans l'instance de la classe associée à la commande
				commandRcvTcpList.get(s).execute(this, args);
                return;
			}
		}
	}

    private String extractFirst(String str) {
        String first = "";
        if (str.length() >= 5) {
            for (int i = 0 ; i < 5 ; i++) {
                first += str.charAt(i);
            }
        }
        return first;
        
    }

    private String[] breakCommand(String command) {
        return command.split(" ");
	}

    public PrintWriter getPrintWriter() {
        return this.out;
    }

    public OutputStream getOutputStream() throws IOException{
        return this.clientSocket.getOutputStream();
    }

    public BufferedReader getBufferedReader() {
        return this.in;
    }


    public void closeSocket() {
        try {
            clientSocket.close();
            Client.isConnected = false;
            clientTCPCreated = false;
            ClientUDP.clientUDPCreated = false;
            Console.disconnectConsole();
            Console.connectedConsole = false;
        } catch (IOException e) {
            DebugLogger.print(DebugType.ERROR, "la fermeture du socket client n'a pas aboutie");
        }
    }
}
