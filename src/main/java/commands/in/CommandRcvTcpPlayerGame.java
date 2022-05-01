package main.java.commands.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.LinkedList;

import main.java.GameInfo;
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
    public void execute(ClientTCP client, LinkedList<Integer> command) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : LIST!");

        if (command.size() < 12) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpPlayerGame/ERREUR] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        // read "m" uint8
        int gameId = command.get(6);
        // read "s" uint8
        int nbrPlayers = command.get(8);

        GameInfo.gameIdNbrPlayers.put(gameId, nbrPlayers);
        GameInfo.gameIdPlayersId.putIfAbsent(gameId, new LinkedList<>());
        GameInfo.listId = gameId;

        DebugLogger.print(DebugType.COM, "SERVER : LIST! " + gameId + " " + nbrPlayers);
        
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
