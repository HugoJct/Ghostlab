package main.java;

import java.util.HashMap;
import java.util.LinkedList;

public class GameInfo {
    
    public static int portUDP;

    public static int nbrGames = 0;
    public static String playerID;
    public static HashMap<Integer, Integer> gameIdNbrPlayers = new HashMap<>();
    public static HashMap<String, Integer> playerIdScore = new HashMap<>();
    public static LinkedList<String> messagesHistory = new LinkedList<>();

    public static LinkedList<GameHistory> gameHistory = new LinkedList<>();
}
