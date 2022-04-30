package main.java.commands.in;

import java.io.IOException;
import java.io.PrintWriter;

import main.java.GameInfo;
import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

/// REGOK m***

public class CommandRcvTcpJoinOK extends CommandTCP{

    public CommandRcvTcpJoinOK(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "Command identified : REGOK");

        int gameId;
        try {
            // read " "
           client.getBufferedReader().read();
            // read "m" uint8
            gameId = client.getBufferedReader().read();

            GameInfo.isInGame = true;
            GameInfo.registredGameId = gameId;

            DebugLogger.print(DebugType.COM, "SERVER : " + args[0] + " " + gameId);
            
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
