package main.java.commands.in;

import java.io.PrintWriter;
import java.io.IOException;

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
        DebugLogger.print(DebugType.CONFIRM, "Commande identifiée : DUNNO");
        DebugLogger.print(DebugType.COM, "SERVER : " + args[0]);
        try {
            // read the three "***" to skip them
            client.getBufferedReader().read();
            client.getBufferedReader().read();
            client.getBufferedReader().read();
        } catch(IOException e) { e.printStackTrace(); }
    }
    
}
