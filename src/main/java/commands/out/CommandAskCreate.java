package main.java.commands.out;

import java.io.PrintWriter;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// NEWPL id port***

public class CommandAskCreate extends Command {

    public CommandAskCreate(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "test send");
        client.getpPrintWriter().write(args[0]);
        client.getpPrintWriter().flush();
    }
    
}
