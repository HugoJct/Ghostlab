package main.java.commands.in;

import java.io.PrintWriter;

import main.java.client.ClientUDP;
import main.java.commands.CommandUDP;

public class CommandRcvUdpGhostPos extends CommandUDP {

    public CommandRcvUdpGhostPos(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientUDP clientTCP, String[] args) {
        
    }
    
}
