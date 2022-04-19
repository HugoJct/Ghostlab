package main.java.commands.out;

import java.io.IOException;
import java.io.PrintWriter;

import main.java.GameInfo;
import main.java.client.ClientTCP;
import main.java.commands.CommandFormatter;
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

        if (args.length < 2) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] Tout les paramètres de la commande ne sont pas renseignés. Rappel : NEWPL id port");
            return;
        }

        if (args[1].length() != 8) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] La taille de votre id doit être d'exactement 8 caractères");
            return;
        }

        String[] command = {args[0], args[1], Integer.toString(GameInfo.portUDP)};

        try {
            client.getOutputStream().write(CommandFormatter.formatForTCP(command));
            client.getOutputStream().flush();
            DebugLogger.print(DebugType.CONFIRM, "CLIENT : " + args[0] + " " + args[1] + " " + GameInfo.portUDP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
