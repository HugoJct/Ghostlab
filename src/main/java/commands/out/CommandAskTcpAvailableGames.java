package main.java.commands.out;

import java.io.IOException;
import java.io.PrintWriter;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.CommandFormatter;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// GAME?***

public class CommandAskTcpAvailableGames extends CommandTCP {

    public CommandAskTcpAvailableGames(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "ask available game command (GAME?)");
        


        try {
            client.getOutputStream().write(CommandFormatter.formatForTCP(args));
            client.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
