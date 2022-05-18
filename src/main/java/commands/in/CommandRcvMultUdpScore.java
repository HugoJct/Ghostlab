package main.java.commands.in;

import java.net.DatagramSocket;
import java.net.InetAddress;

import main.java.GameInfo;
import main.java.client.ClientUDP;
import main.java.commands.CommandUDP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// SCORE id p x y+++

public class CommandRcvMultUdpScore extends CommandUDP {

    @Override
    public void execute(String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : SCORE");

        if(args.length < 5) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvUdpScore/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        GameInfo.playerIdScore.put(args[1], Integer.parseInt(args[2]));

    }

}
