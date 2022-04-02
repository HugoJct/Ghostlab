package main.java.commands.in;

import java.io.IOException;
import java.io.PrintWriter;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;

// PLAYR id***

public class CommandRcvPlayerId extends Command {

    public CommandRcvPlayerId(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {
        String id = "";
        int count = 0;

        // read first char : " "
        try {
            client.getBufferedReader().read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read the id (8 char)
        while(count < 8) {
            try {
                id += client.getBufferedReader().read();
                count++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
