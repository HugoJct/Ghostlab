package main.java.commands.out.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

// MALL? mess***

public class CommandAskTcpSendMessageAll extends CommandTCP {

    public CommandAskTcpSendMessageAll(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : MALL?");

        if (!GameInfo.isInGame) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskTcpSendMessageAll] : impossible d'envoyer un message, vous n'êtes pas dans une partie");
            return;
        }

        if(!GameInfo.hasGameStarted) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskTcpSendMessageAll] : impossible d'envoyer un message, la partie n'est pas commencée");
            return;
        }

        if (args.length < 2) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskTcpSendMessageAll] : il manque des arguments");
            return;
        }
        
        DebugLogger.print(DebugType.COM, "CLIENT : " + args[0] + " " + args[1]);

        try {
            clientTCP.getOutputStream().write((args[0] +" "+args[1]+"***").getBytes());
            clientTCP.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
