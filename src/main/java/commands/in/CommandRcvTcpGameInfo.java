package main.java.commands.in;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.lang.Integer;

import main.java.GameInfo;
import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// OGAME m s***

public class CommandRcvTcpGameInfo extends CommandTCP{

    public CommandRcvTcpGameInfo(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, LinkedList<Integer> command) {
        
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : OGAME");
        
        if (command.size() < 12) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpGameInfo/ERREUR] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        // read "m" : game id
        int m = command.get(6);
    
        // read "s" : nbr players in game m
        int s = command.get(8);

        GameInfo.gameIdNbrPlayers.put(m, s);
        DebugLogger.print(DebugType.COM, "SERVER : OGAME " + m + " " + s);

    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
