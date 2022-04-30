package main.java.commands.out;

import java.io.IOException;
import java.io.PrintWriter;

import main.java.GameInfo;
import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.CommandFormatter;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// LIST? m***

public class CommandAskTcpPlayerGame extends CommandTCP {

    public CommandAskTcpPlayerGame(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        
        DebugLogger.print(DebugType.CONFIRM, "ask player game command (LIST?)");

        if (args.length < 2) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] Le numéro de la partie n'a pas été précisé");
            return;
        }

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

        try {
            client.getOutputStream().write(CommandFormatter.formatForTCP(args));
            client.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
