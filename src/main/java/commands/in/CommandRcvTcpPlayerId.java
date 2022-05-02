package main.java.commands.in;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.GameInfo;
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
    public void execute(ClientTCP client, LinkedList<Integer> command) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : PLAYR");

        String id = "";
        int count = 0;

        if (command.size() < 17) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpPlayerId/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        // read the id (8 char)
        while(count < 8) {
            id += (char) command.get(6 + count).byteValue();
            count++;
        }

        // ajout de l'id à la liste d'id de la hashmap "gameIdPlayersId" dans GameInfo
        LinkedList<String> tmpList = GameInfo.gameIdPlayersId.get(GameInfo.listId);
        tmpList.add(id);
        GameInfo.gameIdPlayersId.put(GameInfo.listId, tmpList);

        DebugLogger.print(DebugType.COM, "SERVER : PLAYR " + id);
                    
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
