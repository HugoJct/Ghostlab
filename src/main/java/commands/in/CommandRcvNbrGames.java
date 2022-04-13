package main.java.commands.in;

import java.io.IOException;
import java.io.PrintWriter;

import main.java.GameInfo;
import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// GAMES n***

public class CommandRcvNbrGames extends Command {

    public CommandRcvNbrGames(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "Command identified : GAMES");
        
        byte b;
        try {
            // read " "
            client.getBufferedReader().read();
            // read "n" uint8
            GameInfo.nbrGames = (Integer.valueOf(client.getBufferedReader().read())).byteValue();

            // read the three "***" to skip them
            client.getBufferedReader().read();
            client.getBufferedReader().read();
            client.getBufferedReader().read();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }
    
}
