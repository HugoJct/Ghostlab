package main.java.commands.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Integer;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;

// OGAME m s***

public class CommandRcvGameInfo extends Command{

    public CommandRcvGameInfo(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        
        int pos = 0;
        int stopCount = 0;
        byte c;
        LinkedList<Byte> byteBuffer = new LinkedList<Byte>();

        while(true) {
           
            if (stopCount == 3) {
                break;
            }

            try {
                c = (Integer.valueOf(client.getBufferedReader().read())).byteValue();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            // "charactère espace"
            if (c == 32) {
                continue;
            }

            // "charactère * "
            if (c == 42) {
                stopCount++;
                continue;
            }

            byteBuffer.add((byte)c);

        }


    }
    
}
