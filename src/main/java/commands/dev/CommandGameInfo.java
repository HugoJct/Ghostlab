package main.java.commands.dev;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Map;

import main.java.GameInfo;
import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class CommandGameInfo extends CommandTCP {

    public CommandGameInfo(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : gameinfo");

        DebugLogger.print(DebugType.HELP, "Nombre de parties trouvées et disponibles : " + GameInfo.nbrGames);
        
        if (GameInfo.gameIdNbrPlayers.isEmpty()) {
            DebugLogger.print(DebugType.HELP, "Pas de parties trouvées");
            return;
        }
        for (Map.Entry<Integer,Integer> entry : GameInfo.gameIdNbrPlayers.entrySet()) {
            DebugLogger.print(DebugType.HELP, "Game nbr : " + entry.getKey() + ", nombre de joueurs : " + entry.getValue());
        }
        
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        // TODO Auto-generated method stub
        
    }
    
}
