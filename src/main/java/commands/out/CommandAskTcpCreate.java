package main.java.commands.out;

import java.io.PrintWriter;
import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;


// NEWPL id port***

public class CommandAskTcpCreate extends CommandTCP {

    public CommandAskTcpCreate(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "create game command (NEWPL)");

        if (args.length < 3) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] Tout les paramètres de la commande ne sont pas renseignés. Rappel : NEWPL id port");
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

        client.getPrintWriter().write(args[0] + " " + args[1] + " " + args[2] + "***");
        client.getPrintWriter().flush();
    }
    
}
