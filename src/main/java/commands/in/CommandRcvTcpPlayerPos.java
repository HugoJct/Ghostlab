package main.java.commands.in;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.GameInfo;
import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// POSIT id x y***

public class CommandRcvTcpPlayerPos extends CommandTCP {

    public CommandRcvTcpPlayerPos(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : POSIT");

        if (command.size() < 25) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpPlayerId/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        String id = "";
        int count = 0;
        String x = "";
        String y = "";


        // read the id (8 char)
        while(count < 8) {
            id += (char) command.get(6 + count).byteValue();
            count++;
        }

        // vérification que le message était destiné au joueur
        if (!id.equals(GameInfo.playerID)) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpPlayerPos/WARNING] : le joueur (" + id + ") envoyé par le serveur n'est pas le joueur courant, cette commande sera ignorée");
            return;
        }

        for (int i = 15 ; i < 18 ; i++) {
            x += (char) command.get(i).byteValue();
        }
        for (int i = 18 ; i < 21 ; i++) {
            y += (char) command.get(i).byteValue();
        }

        try {
            GameInfo.playerIdPosition.put(id, new Integer[] {Integer.parseInt(x), Integer.parseInt(y)});
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpPlayerPos/WARNING] : les informations de coordonnées du joueur n'ont pas été correctement données par le serveur, cette commande sera ignorée");
            return;
        }

        DebugLogger.print(DebugType.COM, "SERVER : POSIT " + id + " " + x + " " + y);
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {

    }
    
}
