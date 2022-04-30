package main.java.commands.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.GameInfo;
import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// LIST! m s***

public class CommandRcvTcpPlayerGame extends CommandTCP {

    public CommandRcvTcpPlayerGame(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "Command identified : LIST!");

        int gameId, nbrPlayers;

        try {
            // read " "
            client.getBufferedReader().read();
            // read "m" uint8
            gameId = client.getBufferedReader().read();
            // read " "
            client.getBufferedReader().read();
            // read "s" uint8
            nbrPlayers = client.getBufferedReader().read();

            GameInfo.gameIdNbrPlayers.put(gameId, nbrPlayers);
            GameInfo.gameIdPlayersId.putIfAbsent(gameId, new LinkedList<>());
            GameInfo.listId = gameId;

            DebugLogger.print(DebugType.COM, "SERVER : " + args[0] + " " + gameId + " " + nbrPlayers);
            
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
