package main.java.commands.dev;

import java.io.PrintWriter;
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
        DebugLogger.print(DebugType.CONFIRM, "Command identified : GAMEINFO");

        DebugLogger.print(DebugType.HELP, "Available games : " + GameInfo.nbrGames);
        
        for (Map.Entry<Integer,Integer> entry : GameInfo.gameIdNbrPlayers.entrySet()) {
            DebugLogger.print(DebugType.HELP, "Game nbr : " + entry.getKey() + ", nbr of players : " + entry.getValue());
        }
        
    }
    
}
