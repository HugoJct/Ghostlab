package main.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;

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
import main.java.commands.in.CommandRcvTcpWelco;
import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.gui.ControlGUI;


public class ClientTCP extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private ControlGUI gui;

    private HashMap<String,CommandTCP> commandRcvTcpList = new HashMap<String,CommandTCP>();
    
    public static boolean clientTCPCreated = false;

    public ClientTCP(String ip, int port, ControlGUI gui) {
        try {
            DebugLogger.print(DebugType.COM, "Création de la connection TCP avec le serveur...");
            this.clientSocket = new Socket(ip, port);
            this.out = new PrintWriter(clientSocket.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.gui = gui;

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
            commandRcvTcpList.put("WELCO", new CommandRcvTcpWelco(out));

            DebugLogger.print(DebugType.COM, "...succès");

            clientTCPCreated = true;
        } catch (UnknownHostException e) {
            DebugLogger.print(DebugType.ERROR, "[ClientTCP/ERREUR] : L'adresse IP de l'hôte ne peut être déterminée");
        } catch (IOException e) {
            DebugLogger.print(DebugType.ERROR,"[ClientTCP/ERREUR] : Numero de PORT INDISPONIBLE ou IP INCONNUE");
        }

    }

    @Override
    public void run() {

        DebugLogger.print(DebugType.CONFIRM, "Début de l'écoute TCP");
        
        boolean ignore = false;

        // tant que le socket est connecté
        while(Client.isConnected) {
            
            try {

                LinkedList<Integer> serverMsg = new LinkedList<>();

                int offsetLimiter = 0;
                int readVal = 0;

                while(offsetLimiter < 3) {

                    readVal = in.read();

                    // si le socket est déconnecté : arrêter de lire
                    if (readVal == -1) {
                        DebugLogger.print(DebugType.CONFIRM, "Server is closed : disconnected");
                        Console.useMessage("killclient");
                        ignore = true;
                        break;
                    }

                    serverMsg.add(readVal);

                    if (readVal == 42) {
                        offsetLimiter++;
                    } else {
                        offsetLimiter = 0;
                    }
                }

                if (!ignore) {
                    useMessage(serverMsg);
                }

            } catch(IOException e) {
                DebugLogger.print(DebugType.CONFIRM, "Socket closed : disconnected");
            }
        }

        try {
            clientSocket.close();
            clientTCPCreated = false;
            Client.disconnect();
        } catch (IOException e) {
            DebugLogger.print(DebugType.ERROR, "la fermeture du socket client n'a pas aboutie");
            System.exit(1);
        }

    }

    // envoie du message sur la sortie vers le serveur
	public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }

    // parsing de la commande
	public void useMessage(LinkedList<Integer> command) {
        
        String commandName = "";
        for (int i = 0; i < 5; i++) {
            commandName += (char) command.get(i).byteValue();
        }
		
		for(String s : commandRcvTcpList.keySet()) {
			if(s.equals(extractFirst(commandName))) {
                // appel de la fonction dans l'instance de la classe associée à la commande
				commandRcvTcpList.get(s).execute(this, command);
                gui.actualise();
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

    public PrintWriter getPrintWriter() {
        return this.out;
    }

    public OutputStream getOutputStream() throws IOException{
        return this.clientSocket.getOutputStream();
    }

    public BufferedReader getBufferedReader() {
        return this.in;
    }


    public synchronized void setGUI(ControlGUI gui) {
        this.gui = gui;
    }
}
