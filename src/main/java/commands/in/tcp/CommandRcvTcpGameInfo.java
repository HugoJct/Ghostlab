package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.lang.Integer;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.game.Games;

// OGAME m s***

public class CommandRcvTcpGameInfo extends CommandTCP{

    public CommandRcvTcpGameInfo(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, LinkedList<Integer> command) {
        
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : OGAME");
        
        if (command.size() < 12) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpGameInfo/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        // read "m" : game id
        int m = command.get(6);
    
        // read "s" : nbr players in game m
        int s = command.get(8);

        int width = -1;
        int height = -1;

        try {
            width = GameInfo.games.get(m).getWidth();
            height = GameInfo.games.get(m).getHeight();
            GameInfo.games.add(m, new Games(s, height, width));
        } catch (IndexOutOfBoundsException e) {
            GameInfo.games.add(m, new Games(s, height, width));
            return;
        }

        DebugLogger.print(DebugType.COM, "SERVER : OGAME " + m + " " + s);

    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
