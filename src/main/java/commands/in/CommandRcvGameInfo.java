package main.java.commands.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Integer;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// OGAME m s***

public class CommandRcvGameInfo extends Command{

    public CommandRcvGameInfo(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        
        DebugLogger.print(DebugType.CONFIRM, "Command identified : OGAME");

        int stopCount = 0;
        byte b;
        LinkedList<Integer> byteUint8List = new LinkedList<Integer>();

        while(true) {
           
            if (stopCount == 3) {
                break;
            }

            try {
                b = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            // "charactère espace"
            if (b == 32) {
                continue;
            }

            // "charactère * "
            if (b == 42) {
                stopCount++;
                continue;
            }

            byteUint8List.add((byte)b & 0xFF);

        }


    }
    
}
