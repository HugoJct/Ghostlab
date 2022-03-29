package main.java.commands.dev;

import java.io.PrintWriter;

import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class CommandHelp extends Command {

    public CommandHelp(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "help command");
    }
    
}
