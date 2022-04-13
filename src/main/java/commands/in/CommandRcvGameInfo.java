package main.java.commands.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Integer;

import main.java.GameInfo;
import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// OGAME m s***

public class CommandRcvGameInfo extends Command{

    public CommandRcvGameInfo(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        
        DebugLogger.print(DebugType.CONFIRM, "Command identified : OGAME");

        int stopCount = 0;
        byte b;
        
        // read " "
        try {
            // read "m" : game id
            int m = (Integer.valueOf(client.getBufferedReader().read())).byteValue();

            // read " "
            client.getBufferedReader().read();

            // read "s" : nbr players in game m
            int s = (Integer.valueOf(client.getBufferedReader().read())).byteValue();

            GameInfo.gameIdNbrPlayers.put(m, s);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }




    }
    
}
