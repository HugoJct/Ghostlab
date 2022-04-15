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

        byte[] bytesToSend = CommandFormatter.formatForTCP(args);    //required size for this message
        /*String regis = "REGIS";     //5
        String stars = "***";       //3
        String name = args[1];      //8
        String port = args[2];      //4
        String gameID = args[3];    //1
        String space = " ";         //3 spaces
                                // = 24
        int index = 0;
        
        //WRITING REGIS
        for(int i=0;i<regis.length();i++)
            bytesToSend[index++] = regis.getBytes()[i];

        //writing space
        bytesToSend[index++] = space.getBytes()[0];

        //writing name
        for(int i=0;i<name.length();i++)
            bytesToSend[index++] = name.getBytes()[i];

        //writing space
        bytesToSend[index++] = space.getBytes()[0];

        for(int i=0;i<port.length();i++)
            bytesToSend[index++] = port.getBytes()[i];

        //writing space
        bytesToSend[index++] = space.getBytes()[0];

        //writing the game id
        bytesToSend[index++] = (byte) Integer.parseInt(gameID);

        //writing stars
        for(int i=0;i<stars.length();i++)
            bytesToSend[index++] = stars.getBytes()[i];*/

        try {

            client.getOutputStream().write(bytesToSend);
            client.getOutputStream().flush();
        } catch(IOException e) { }
    }
    
}
