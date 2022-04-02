package main.java.commands.in;

import java.io.IOException;
import java.io.PrintWriter;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// REGNO***

public class CommandRcvJoinNO extends Command{

    public CommandRcvJoinNO(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "Command identified : REGNO");
    }
    
}
