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
        
        if (args.length < 3) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] Tout les paramètres de la commande ne sont pas renseignés. Rappel : REGIS id port m");
            return;
        }

        if (args[1].length() != 8) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] La taille de votre id doit être d'exactement 8 caractères");
            return;
        }

        try {
            int i = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] Le type du numéro de la partie n'est pas conforme");
            return;
        }

        if (!GameInfo.gameIdNbrPlayers.containsKey(Integer.parseInt(args[2]))) {
            DebugLogger.print(DebugType.ERROR, "[ATTENTION/CommandAskJoin] La partie donnée n'existe pas");
            return;
        }

        String[] commande = {args[0], args[1], Integer.toString(GameInfo.portUDP), args[2]};

        try {
            client.getOutputStream().write(CommandFormatter.formatForTCP(commande));
            client.getOutputStream().flush();
            DebugLogger.print(DebugType.CONFIRM, "CLIENT : " + args[0] + " " + args[1] + " " + GameInfo.portUDP + " " + args[2]);
        } catch(IOException e) { 
            e.printStackTrace();
        }

    }
    
}
