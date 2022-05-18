package main.java;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GameHistory {
    private final String playerID;
    private final HashMap<String, Integer> playerIdScore;
    private final HashMap<String, Integer> playerIdScoreIncr;
    private final LinkedList<String> messagesHistory;
    private final String winner;

    public GameHistory(String playerID, HashMap<String, Integer> playerIdScore, LinkedList<String> messagesHistory, String winner) {
        this.playerID = playerID;
        this.playerIdScoreIncr = setPlayerIdScoreIncr();
        this.playerIdScore = playerIdScore;
        this.messagesHistory = messagesHistory;
        this.winner = winner;
    }


    public HashMap<String, Integer> setPlayerIdScoreIncr() {
        if (playerIdScore == null) {
            return null;
        }
        HashMap<String, Integer> scores = new HashMap<>();
        HashMap<String, Integer> playerIdScoreCpy = playerIdScore;
        String maxPlayer;
        while (playerIdScoreCpy.size() > 0) {
            maxPlayer = Collections.max(playerIdScoreCpy.entrySet(), Map.Entry.comparingByValue()).getKey();
            scores.put(maxPlayer, playerIdScoreCpy.get(maxPlayer));
            playerIdScoreCpy.remove(Collections.max(playerIdScoreCpy.entrySet(), Map.Entry.comparingByValue()).getKey());
        }
        return scores;
    }

    public String getPlayerID() {
        return playerID;
    }
    public HashMap<String, Integer> getPlayerIdScoreIncr() {
        return playerIdScoreIncr;
    }
    public LinkedList<String> getMessagesHistory() {
        return messagesHistory;
    }
    public String getWinner() {
        return winner;
    }
    public int getWinnerScore() {
        return playerIdScore.get(winner);
    }

}
