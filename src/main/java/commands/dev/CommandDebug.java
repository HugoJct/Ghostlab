package main.java.commands.dev;

import java.io.PrintWriter;

import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;

public class CommandDebug extends Command {

    public CommandDebug(PrintWriter pw) {
        super(pw);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {  
        //DebugLogger.print(DebugLogger., str);
        
    }
    
}
