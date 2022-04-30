package main.java.commands.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.GameInfo;
import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// PLAYR id***

public class CommandRcvTcpPlayerId extends CommandTCP {

    public CommandRcvTcpPlayerId(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "Command identified : PLAYR");

        String id = "";
        int count = 0;

        // read first char : " "
        try {
            client.getBufferedReader().read();
        } catch (IOException e) {
            e.printStackTrace();
        }

            try {       
                // read the id (8 char)
                while(count < 8) {
                    id += client.getBufferedReader().read();
                    count++;
                }
                // ajout de l'id à la liste d'id de la hashmap "gameIdPlayersId" dans GameInfo
                LinkedList<String> tmpList = GameInfo.gameIdPlayersId.get(GameInfo.listId);
                tmpList.add(id);
                GameInfo.gameIdPlayersId.put(GameInfo.listId, tmpList);
            } catch (IOException e) {
                e.printStackTrace();
            }


        try {
            // read the three "***" to skip them
            client.getBufferedReader().read();
            client.getBufferedReader().read();
            client.getBufferedReader().read();
        } catch (IOException e) {
            e.printStackTrace();
        }
                    
    }
    
}
