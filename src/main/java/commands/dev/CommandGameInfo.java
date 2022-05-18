package main.java.commands.dev;

import java.util.Map;

import main.java.GameInfo;
import main.java.commands.CommandDev;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class CommandGameInfo extends CommandDev {

    @Override
    public void execute(String[] args) {
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
    
}
