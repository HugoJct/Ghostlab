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

        int GhostPosX = Integer.parseInt(args[1]);
        int GhostPosY = Integer.parseInt(args[2]);

    }
    
}
