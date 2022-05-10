package main.java.commands.in;

import java.net.DatagramSocket;
import java.net.InetAddress;

import main.java.GameHistory;
import main.java.GameInfo;
import main.java.client.ClientUDP;
import main.java.commands.CommandUDP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// ENDGA id p+++

public class CommandRcvUdpEndGame extends CommandUDP {

    public CommandRcvUdpEndGame(DatagramSocket socket, InetAddress addr) {
        super(socket, addr);
    }

    @Override
    public void execute(ClientUDP clientTCP, String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : ENDGAME");

        if (args.length < 3) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvUdpEndGame/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        DebugLogger.print(DebugType.MESSAGE, "Partie terminée, " + args[1] + " gagne avec " + Integer.parseInt(args[2] + " points !"));

        GameInfo.gameHistory.add(new GameHistory(GameInfo.playerID, GameInfo.playerIdScore, GameInfo.messagesHistory, args[1]));
        GameInfo.playerIdScore.put(args[1], Integer.parseInt(args[2]));

        GameInfo.playerID = "";
        GameInfo.playerIdScore.clear();
        GameInfo.messagesHistory.clear();

        // retour au lobby

    }

    
}
