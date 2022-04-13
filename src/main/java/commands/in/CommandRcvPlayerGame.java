package main.java.commands.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// LIST! m s***

public class CommandRcvPlayerGame extends Command {

    public CommandRcvPlayerGame(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "Command identified : LIST!");

        byte b;
        LinkedList<Integer> byteList = new LinkedList<Integer>();
        try {
            // read " "
            b = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            // read "m" uint8
            b = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            byteList.add((byte)b & 0xFF);
            // read " "
            b = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            // read "s" uint8
            b = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            byteList.add((byte)b & 0xFF);

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
