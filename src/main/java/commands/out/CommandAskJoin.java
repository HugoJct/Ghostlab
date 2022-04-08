package main.java.commands.out;

import java.io.PrintWriter;

import main.java.GameInfo;
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

        DebugLogger.print(DebugType.CONFIRM, "ask join game command (REGIS)");

        if (args.length < 4) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] Tout les paramètres de la commande ne sont pas renseignés. Rappel : REGIS id port m");
            return;
        }

        if (args[1].length() > 8) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] La taille de votre id doit être de MAX 8 caractères");
            return;
        }

        if (args[2].length() > 4) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] La taille du port doit être de MAX 9999");
            return;
        }

        try {
            int i = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] Le type de port donné n'est pas conforme");
            return;
        }

        try {
            int i = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] Le type du numéro de la partie n'est pas conforme");
            return;
        }

        if (!GameInfo.gameIdNbrPlayers.containsKey(Integer.parseInt(args[3]))) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] La partie donnée n'existe pas");
            return;
        }

        client.getPrintWriter().write(args[0] + args[1] + args[2] + (byte)Integer.parseInt(args[3]) + "***");
        client.getPrintWriter().flush();
    }
    
}
