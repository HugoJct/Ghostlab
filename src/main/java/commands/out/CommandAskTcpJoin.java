package main.java.commands.out;

import java.io.PrintWriter;
import main.java.GameInfo;
import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.commands.CommandFormatter;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import java.io.IOException;


// REGIS id port m***

public class CommandAskTcpJoin extends CommandTCP {

    public CommandAskTcpJoin(PrintWriter pw) {
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

        try {
            client.getOutputStream().write(CommandFormatter.formatForTCP(args));
            client.getOutputStream().flush();
        } catch(IOException e) { 
            e.printStackTrace();
        }
    }
    
}
