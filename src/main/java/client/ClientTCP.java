package main.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import main.java.commands.Command;
import main.java.commands.in.CommandRcvDunno;
import main.java.commands.in.CommandRcvGameInfo;
import main.java.commands.in.CommandRcvJoinNO;
import main.java.commands.in.CommandRcvJoinOK;
import main.java.commands.in.CommandRcvMapSize;
import main.java.commands.in.CommandRcvNbrGames;
import main.java.commands.in.CommandRcvPlayerGame;
import main.java.commands.in.CommandRcvPlayerId;
import main.java.commands.in.CommandRcvUnregisterOK;
import main.java.console.DebugLogger;
import main.java.console.DebugType;


public class ClientTCP extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean isConnected;

    private HashMap<String,Command> commandRcvList = new HashMap<String,Command>();
    
    public ClientTCP(String ip, int port) {
        try {
            this.clientSocket = new Socket(ip, port);
            this.out = new PrintWriter(clientSocket.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.isConnected = true;
        } catch (UnknownHostException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("ERREUR : Numero de PORT INDISPONIBLE ou IP INCONNUE");
        }

        // remplissage de la liste de commandes recevables
        commandRcvList.put("DUNNO", new CommandRcvDunno(out));
        commandRcvList.put("OGAME", new CommandRcvGameInfo(out));
        commandRcvList.put("REGNO", new CommandRcvJoinNO(out));
        commandRcvList.put("REGOK", new CommandRcvJoinOK(out));
        commandRcvList.put("SIZE!", new CommandRcvMapSize(out));
        commandRcvList.put("GAMES", new CommandRcvNbrGames(out));
        commandRcvList.put("LIST!", new CommandRcvPlayerGame(out));
        commandRcvList.put("PLAYR", new CommandRcvPlayerId(out));
        commandRcvList.put("UNROK", new CommandRcvUnregisterOK(out));

    }

    @Override
    public void run() {
        try {

            String serverMsg = "";

            // tant que le socket est connecté
        	while(isConnected) {
                serverMsg = "";

                int pos = 0;
                int readVal = 0;

                while(true) {

                    if (pos == 5) {
                        break;
                    }

                    readVal = in.read();

                    // si le socket est déconnecté : arrêter de lire
                    if (readVal == -1) {
                        DebugLogger.print(DebugType.CONFIRM, "Server is closed : disconnected !");
                        isConnected = false;
                        break;
                    }

                    serverMsg += (char)readVal;
                    pos++;
                }
                useMessage(serverMsg);
        	}
        } catch(IOException e) {
            DebugLogger.print(DebugType.CONFIRM, "Socket closed");
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
		
		for(String s : commandRcvList.keySet()) {
			if(s.equals(extractFirst(args[0]))) {
                // appel de la fonction dans l'instance de la classe associée à la commande
				commandRcvList.get(s).execute(this, args);
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

    public boolean isConnected() {
        return isConnected;
    }

    public void closeSocket() {
        try {
            clientSocket.close();
            isConnected = false;
        } catch (IOException e) {
            DebugLogger.print(DebugType.ERROR, "la fermeture du socket client n'a pas aboutie");
        }
    }
}
