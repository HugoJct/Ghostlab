package main.java.commands.in;

import java.io.PrintWriter;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// DUNNO***

public class CommandRcvTcpDunno extends CommandTCP {

    public CommandRcvTcpDunno(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "Command identified : DUNNO");
    }
    
}