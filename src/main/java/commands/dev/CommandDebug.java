package main.java.commands.dev;

import java.io.PrintWriter;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class CommandDebug extends CommandTCP {

    public CommandDebug(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "debug command");

        if (args.length == 3) {
            int value;
            String type;
            try {
                value = Integer.parseInt(args[2]);
                if (args[1] instanceof String) {
                    type = args[1];
                    switch(type) {
                        case "ERROR": 
                            if (value == 0) {
                                System.out.println("debug ERROR mode : disabled");  
                                DebugLogger.typeMap.replace(DebugType.ERROR, false);
                            }
                            else if (value == 1) {
                                System.out.println("debug ERROR mode : enabled");  
                                DebugLogger.typeMap.replace(DebugType.ERROR, true);
                            }
                            return;
                        case "ALL": 
                            if (value == 0) {
                                System.out.println("debug ALL modes : disabled");  
                                DebugLogger.typeMap.replace(DebugType.ALL, false);
                            }
                            else if (value == 1) {
                                System.out.println("debug ALL modes : enabled");  
                                DebugLogger.typeMap.replace(DebugType.ALL, true);
                            }
                            return;
                        case "CONFIRM": 
                            if (value == 0) {
                                System.out.println("debug UI mode : disabled");  
                                DebugLogger.typeMap.replace(DebugType.CONFIRM, false);
                            }
                            else if (value == 1) {
                                System.out.println("debug UI mode : enabled");  
                                DebugLogger.typeMap.replace(DebugType.CONFIRM, true);
                            }
                            return;
                    }
                }
            } catch(NumberFormatException e) {}
        }
            // list every debug type state
            else if (args.length == 2) {
                if (args[1].equals("LIST")) {
                    for(int i = -1 ; i<DebugLogger.typeMap.size()-1 ; i++) {
                        DebugLogger.print(DebugType.HELP, "Debug type (" + DebugLogger.typeMapDirectory.get(i) + ", " + i + ") : " + DebugLogger.typeMap.get(i));
                    }
                    return;
                }
            }
            DebugLogger.print(DebugType.ERROR, "DEBUG : Command syntax error");  
    }
    
}
