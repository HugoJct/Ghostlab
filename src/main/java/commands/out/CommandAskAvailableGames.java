package main.java.commands.out;

import java.io.PrintWriter;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// GAME?***

public class CommandAskAvailableGames extends Command {

    public CommandAskAvailableGames(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "ask available game command (GAME?)");
        
        client.getPrintWriter().write(args[0] + "***");
        client.getPrintWriter().flush();

    }
    
}
