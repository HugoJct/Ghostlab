package main.java.commands.in.udp_multicast;

import main.java.commands.CommandUDP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.game.Player;

// SCORE id p x y+++

public class CommandRcvMultUdpScore extends CommandUDP {

    @Override
    public void execute(String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : SCORE");

        if(args.length < 5) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvUdpScore/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        GameInfo.players.put(args[1], new Player(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4])));

        DebugLogger.print(DebugType.COM, "SERVER : " + args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4]);

    }

}
