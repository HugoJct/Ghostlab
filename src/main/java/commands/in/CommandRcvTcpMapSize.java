package main.java.commands.in;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.nio.ByteBuffer;

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
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpMapSize/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        // read "m" uint8
        int m = command.get(6);
        // read "h" uint16
        int h = ByteBuffer.wrap(new byte[] { command.get(7).byteValue(), command.get(8).byteValue() }).getInt();
        // read "w" uint16
        int w = ByteBuffer.wrap(new byte[] { command.get(11).byteValue(), command.get(12).byteValue() }).getInt();

        DebugLogger.print(DebugType.COM, "SERVER : SIZE! " + m + " " + h + " " + w);

    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
