package main.java.commands.in;

import java.net.DatagramSocket;
import java.net.InetAddress;

import main.java.GameInfo;
import main.java.client.ClientUDP;
import main.java.commands.CommandUDP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// MESSA id mess+++

public class CommandRcvUdpMessage extends CommandUDP {

    public CommandRcvUdpMessage(DatagramSocket socket, InetAddress addr) {
        super(socket, addr);
    }

    @Override
    public void execute(ClientUDP clientTCP, String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : MESSA");

        GameInfo.messagesHistory.add(args[1] + " : " + args[2]);

        DebugLogger.print(DebugType.MESSAGE, args[1] + " : " + args[2]);
    }
    
}
