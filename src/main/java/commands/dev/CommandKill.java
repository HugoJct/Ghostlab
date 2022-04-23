package main.java.commands.dev;

import java.io.PrintWriter;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class CommandKill extends CommandTCP {

    public CommandKill(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "kill command");

        if (Client.isConnected) {
            clientTCP.closeSocket();
        }

        System.exit(0);
    }
    
}
