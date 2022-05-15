package main.java.commands.out;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.GameInfo;
import main.java.client.ClientTCP;
import main.java.commands.CommandFormatter;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// UNREG***

public class CommandAskUnregister extends CommandTCP{

    public CommandAskUnregister(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : ask unregistration command (UNREG)");

        if (!GameInfo.isInGame) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskUnregister] : impossible de se déconnecter, vous n'êtes pas dans une partie");
        }
        else if(GameInfo.hasGameStarted) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskUnregister] : impossible de se déconnecter, la partie a déjà commencé");
        }
        else {
            try {
                clientTCP.getOutputStream().write(CommandFormatter.formatForTCP(new String[] {args[0]}));
                clientTCP.getOutputStream().flush();
                DebugLogger.print(DebugType.COM, "CLIENT : " + args[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        // TODO Auto-generated method stub
        
    }
    
}
