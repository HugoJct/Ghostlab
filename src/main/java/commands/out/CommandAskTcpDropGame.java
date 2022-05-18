package main.java.commands.out;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandFormatter;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// IQUIT***

public class CommandAskTcpDropGame extends CommandTCP {

    public CommandAskTcpDropGame(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : IQUIT");

        try {
            clientTCP.getOutputStream().write(CommandFormatter.formatForTCP(new String[] {"IQUIT"}));
            clientTCP.getOutputStream().flush();
            DebugLogger.print(DebugType.COM, "CLIENT : " + "IQUIT");
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
