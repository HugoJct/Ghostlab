package main.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import main.java.commands.Command;
import main.java.commands.in.CommandRcvGameInfo;

public class ClientTCP extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean isConnected;

    private HashMap<String,Command> commandList = new HashMap<String,Command>();
    
    public ClientTCP(String ip, int port) {
        try {
            this.clientSocket = new Socket(ip, port);
            this.out = new PrintWriter(clientSocket.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        commandList.put("GAMES",new CommandRcvGameInfo(out));

        // AJOUTER LES AUTRES COMMANDES
    }

    @Override
    public void run() {
        try {

            String serverMsg;

        	while(isConnected) {

            	serverMsg = in.readLine();

                // if the socket is disconnected
        		if(serverMsg == null) {
                	System.out.println("Disconnected !");
                	isConnected = false;
                	break;
            	}

            	System.out.println("Server wrote: "+ serverMsg);
                useMessage(serverMsg);

        	}
        } catch(IOException e) {
            System.out.println("Socket closed by the server.");
        }
    }

    // This method sends a message to the client handled by the instance of the class
	public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }

	public void useMessage(String command) {
        String[] args = breakCommand(command);
		
		for(String s : commandList.keySet()) {
			if(s.equals(args[0])) {
				commandList.get(s).execute(this, args);
                return;
			}
		}
	}

    private String[] breakCommand(String command) {
        return command.split(" ");
	}
}
