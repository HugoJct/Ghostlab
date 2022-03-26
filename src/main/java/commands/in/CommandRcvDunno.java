package main.java.commands.in;

import java.io.PrintWriter;

import main.java.client.ClientTCP;
import main.java.commands.Command;

// DUNNO***

public class CommandRcvDunno extends Command {

    public CommandRcvDunno(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        
    }
    
}
