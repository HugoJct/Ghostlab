package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.game.Player;

// MOVEF x y p***

public class CommandRcvTcpNewPosPoints extends CommandTCP {

    public CommandRcvTcpNewPosPoints(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : MOVEF");

        if (command.size() < 21) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpNewPos/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }
    
        String x = "";
        String y = "";
        String points = "";
        
        for (int i = 6 ; i < 9 ; i++) {
            x += (char) command.get(i).byteValue();
        }
        for (int i = 10 ; i < 13 ; i++) {
            y += (char) command.get(i).byteValue();
        }
        for (int i = 14 ; i < 18 ; i++) {
            points += (char) command.get(i).byteValue();
        }
    
        try {
            GameInfo.players.put(GameInfo.playerID, new Player(Integer.parseInt(points), Integer.parseInt(x), Integer.parseInt(y)));
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpPlayerPos/WARNING] : les informations de coordonnées du joueur n'ont pas été correctement données par le serveur, cette commande sera ignorée");
            return;
        }

        try {
            GameInfo.players.put(GameInfo.playerID, new Player(Integer.parseInt(points), Integer.parseInt(x), Integer.parseInt(y)));
            /* ou ?
            GameInfo.playerIdScore.put(GameInfo.playerID, newPoints); */
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpPlayerPos/WARNING] : les informations de points données ne sont pas conformes, cette commande sera ignorée");
            return;
        }

        DebugLogger.print(DebugType.COM, "SERVER : MOVEF " + x + " " + y + " " + points);
        
    }
    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }

    
}
