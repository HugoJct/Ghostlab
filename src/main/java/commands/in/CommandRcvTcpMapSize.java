package main.java.commands.in;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// SIZE! m h w***

public class CommandRcvTcpMapSize extends CommandTCP {

    public CommandRcvTcpMapSize(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, LinkedList<Integer> command) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : SIZE!");
        
        if (command.size() < 16) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpMapSize/ERREUR] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        // read "m" uint8
        int m = command.get(6);
        // read "h" uint16
        int h = ((command.get(8) & 0xFF) << 8) + (command.get(9) & 0xFF);
        // read "w" uint16
        int w = ((command.get(11) & 0xFF) << 8) + (command.get(12) & 0xFF);

        DebugLogger.print(DebugType.COM, "SERVER : SIZE! " + m + " " + h + " " + w);

    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
