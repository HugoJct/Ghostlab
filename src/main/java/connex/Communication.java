package main.java.connex;

import main.java.client.Client;
import main.java.commands.Command;
import main.java.commands.in.CommandRcvGameInfo;

import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;


public class Communication implements Runnable{

    Socket s;
    BufferedReader in;
    PrintWriter out;
    Client client;

    private HashMap<String,Command> commandList = new HashMap<String,Command>();


    public Communication(Client client) {
        this.client = client;
    	try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream());
        } catch( IOException e) {
            e.printStackTrace();
        }

        commandList.put("GAMES",new CommandRcvGameInfo(out));

        // AJOUTER LES AUTRES COMMANDES

    }

    // This method sends a message to the client handled by the instance of the class
	public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }

    public PrintWriter getOutput() {
        return this.out;
    }

	@Override
    public void run() {
        
        try {

            String serverMsg;

        	while(client.isConnected()) {

            	serverMsg = in.readLine();

                // if the socket is disconnected
        		if(serverMsg == null) {
                	System.out.println("Disconnected !");
                	client.setConnection(false);
                	break;
            	}

            	System.out.println("Server wrote: "+ serverMsg);
                useMessage(serverMsg);

        	}
        } catch(IOException e) {
            System.out.println("Socket closed by the server.");
        }
    }

	public void useMessage(String command) {
        String[] args = breakCommand(command);
		
		for(String s : commandList.keySet()) {
			if(s.equals(args[0])) {
				commandList.get(s).execute(client, args);
                return;
			}
		}
	}

	private String[] breakCommand(String command) {
		String delims = "[ ]+";
		String[] args = command.split(delims);
		return args;
	}

}
