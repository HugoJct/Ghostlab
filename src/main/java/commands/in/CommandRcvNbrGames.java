package main.java.commands.in;

import java.io.PrintWriter;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;

// GAMES n***

public class CommandRcvNbrGames extends Command {

    public CommandRcvNbrGames(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        
    }
    
}
