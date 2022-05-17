package main.java;

import java.util.HashMap;
import java.util.LinkedList;

public class GameInfo {
    
    // SAUVEGARDE DES INFORMATIONS


    /*
     * informations client
     */

    // le port UDP utilisé par le client
    public static int portUDP;

    public static int portMulticast = -1;
    public static String ipMulticast = "";

    // marque l'id du client joueur
    public static String playerID;


    /*
     * informations partie courante
     */

    // marque si l'on est inscrit à une partie ou non
    public static boolean isInGame = false;

    //marque si la partie registredGameId dans laquelle on est inscrit a été lancée ou non
    public static boolean hasGameStarted = false;

    // marque l'id de la partie à la quelle on est inscrit
    public static int registredGameId = -1;

    // marque la liste des joueurs présents dans chaque partie clé
    public static HashMap<Integer, LinkedList<String>> gameIdPlayersId = new HashMap<>();
    // marque le dernier id de la partie pour laquelle nous avons reçu LIST!
    public static int listId = -1;

    // marque le nombre de joueurs dans la partie registredGameId
    public static int nbrPlayers = -1;

    // marque l'historique des messages de la partie en cours registredGameId
    public static LinkedList<String> messagesHistory = new LinkedList<>();

    // marque la taille en hauteur du labyrinthe
    public static int gameHeight = -1;

    // marque la taille en largeur du labyrinthe
    public static int gameWidth = -1;

    // marque la position des joueurs dans le labyrinthe de la partie registredGameId
    public static HashMap<String, Integer[]> playerIdPosition = new HashMap<>();

        // marque le score de chaque joueur de la partie registredGameId
        public static HashMap<String, Integer> playerIdScore = new HashMap<>();



    /*
     * informations générales parties disponibles
     */

    // marque le nombre de parties disponibles
    public static int nbrGames = 0;
    
    // marque le nombre de joueurs inscrits dans chaque partie disponible
    public static HashMap<Integer, Integer> gameIdNbrPlayers = new HashMap<>();

    
    /*
     * historique des parties terminées
     */

    public static LinkedList<GameHistory> gameHistory = new LinkedList<>();

    public static int nbrGhosts = -1;

 
    public static void clear() {
        gameIdNbrPlayers.clear();
        isInGame = false;
        hasGameStarted = false;
        gameIdPlayersId.clear();
        listId = -1;
        nbrGames = -1;
        portUDP = -1;
        playerIdScore.clear();
        messagesHistory.clear();
        playerID = "";
        registredGameId = -1;
        ipMulticast = "";
        portUDP = -1;
        gameHeight = -1;
        gameWidth = -1;
        nbrPlayers = -1;
        playerIdPosition.clear();
        nbrGhosts = -1;
    }
    

}
