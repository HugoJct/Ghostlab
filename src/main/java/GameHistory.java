package main.java;

import java.util.HashMap;
import java.util.LinkedList;

public class GameHistory {
    private final String playerID;
    private final HashMap<String, Integer> playerIdScore;
    private final LinkedList<String> messagesHistory;
    private final String winner;

    public GameHistory(String playerID, HashMap<String, Integer> playerIdScore, LinkedList<String> messagesHistory, String winner) {
        this.playerID = playerID;
        this.playerIdScore = playerIdScore;
        this.messagesHistory = messagesHistory;
        this.winner = winner;
    }

}
