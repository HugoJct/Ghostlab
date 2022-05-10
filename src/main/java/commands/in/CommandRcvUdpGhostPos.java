package main.java.commands.in;

import java.net.DatagramSocket;
import java.net.InetAddress;

import main.java.client.ClientUDP;
import main.java.commands.CommandUDP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// GHOST x y+++

public class CommandRcvUdpGhostPos extends CommandUDP {

    public CommandRcvUdpGhostPos(DatagramSocket socket, InetAddress addr) {
        super(socket, addr);
    }

    @Override
    public void execute(ClientUDP clientTCP, String[] args) {
        
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : GHOST");

        if (args.length < 3) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvUdpGhostPos/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        try {
            int GhostPosX = Integer.parseInt(args[1]);
            int GhostPosY = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvUdpGhostPos/ERROR] : les informations de coordonnées données par le serveur ne sont pas conformes, cette commande sera ignorée");
        }


    }
    
}
