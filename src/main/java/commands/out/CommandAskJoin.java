package main.java.commands.out;

import java.io.PrintWriter;

import main.java.GameInfo;
import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// REGIS id port m***

public class CommandAskJoin extends Command {

    public CommandAskJoin(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {

        if (args.length < 4) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION] Tout les paramètres de la commande ne sont pas renseignés. Rappel : REGIS id port m");
            return;
        }

        if (!GameInfo.gameIdNbrPlayers.containsKey(Integer.parseInt(args[3]))) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION] La partir donnée n'existe pas");
            return;
        }
    }
    
}
