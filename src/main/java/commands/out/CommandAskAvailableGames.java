package main.java.commands.out;

import java.io.PrintWriter;

import main.java.client.Client;
import main.java.commands.Command;

public class CommandAskAvailableGames extends Command {

    public CommandAskAvailableGames(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(Client client, String[] args) {
        
    }
    
}
