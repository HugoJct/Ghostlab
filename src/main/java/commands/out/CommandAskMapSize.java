package main.java.commands.out;

import java.io.PrintWriter;

import main.java.GameInfo;
import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// SIZE? m***

public class CommandAskMapSize extends Command {

    public CommandAskMapSize(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        
        DebugLogger.print(DebugType.CONFIRM, "ask map size command (SIZE?)");

        try {
            int i = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] Le type du numéro de la partie n'est pas conforme");
            return;
        }

        if (!GameInfo.gameIdNbrPlayers.containsKey(Integer.parseInt(args[1]))) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] La partie donnée n'existe pas");
            return;
        }

        client.getPrintWriter().write(args[0] + (byte)Integer.parseInt(args[1]) + "***");
        client.getPrintWriter().flush();
        
    }
    
}
