package main.java.commands.in;

import java.io.PrintWriter;

import main.java.client.Client;
import main.java.commands.Command;

public class CommandRcvJoinOK extends Command{

    public CommandRcvJoinOK(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(Client client, String[] args) {
        
    }
    
}
