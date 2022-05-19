package main.java.commands.in.udp;

import main.java.commands.CommandUDP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

// MESSP id2 mess+++

public class CommandRcvUdpPrivateMess extends CommandUDP {

    @Override
    public void execute(String[] args) {
        
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : MESSP");

        if (args.length < 3) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvUdpPrivateMess/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        DebugLogger.print(DebugType.MESSAGE, "[PRIVE] - " + args[1] + " : " + args[2]);

        GameInfo.messagesHistory.add("[PRIVE] - " + args[1] + " : " + args[2]);
        
    }
    
}
