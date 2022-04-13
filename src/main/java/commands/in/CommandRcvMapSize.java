package main.java.commands.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.LinkedList;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// SIZE! m h w***

public class CommandRcvMapSize extends Command {

    public CommandRcvMapSize(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "Command identified : SIZE!");
        
        byte b1;
        byte b2;
        LinkedList<Integer> byteList = new LinkedList<Integer>();
        try {
            // read " "
            b1 = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            // read "m" uint8
            b1 = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            byteList.add((byte)b1 & 0xFF);
            // read " "
            b1 = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            // read "h" uint16
            b1 = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            b2 = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            byteList.add(((b1 & 0xFF) << 8) + (b2 & 0xFF));
            // read "w" uint16
            b1 = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            b2 = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            byteList.add(((b1 & 0xFF) << 8) + (b2 & 0xFF));

            // read the three "***" to skip them
            client.getBufferedReader().read();
            client.getBufferedReader().read();
            client.getBufferedReader().read();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }
    
}
