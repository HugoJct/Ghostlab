package main.java.commands.in;

import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;

import main.java.client.ClientUDP;
import main.java.commands.CommandFormatter;
import main.java.commands.CommandUDP;

// GHOST x y+++

public class CommandRcvUdpGhostPos extends CommandUDP {

    public CommandRcvUdpGhostPos(DatagramSocket socket, InetAddress addr) {
        super(socket, addr);
    }

    @Override
    public void execute(ClientUDP clientTCP, String[] args) {
        
    }
    
}
