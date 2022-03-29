package main.java.commands.in;

import java.io.PrintWriter;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;

// UNROK m***

public class CommandRcvUnregisterOK extends Command {

    public CommandRcvUnregisterOK(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        
    }
    
}
