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

    // marque l'id du client joueur
    public static String playerID;


    /*
     * informations partie courante
     */

    // marque si l'on est inscrit à une partie ou non
    public static boolean isInGame = false;

    // marque l'id de la partie à la quelle on est inscrit
    public static int registredGameId;

    // marque le score de chaque joueur de la partie registredGameId
    public static HashMap<String, Integer> playerIdScore = new HashMap<>();

    // marque la liste des joueurs présents dans chaque partie clé
    public static HashMap<Integer, LinkedList<String>> gameIdPlayersId = new HashMap<>();
    // marque le dernier id de la partie pour laquelle nous avons reçu LIST!
    public static int listId;

    // marque l'historique des messages de la partie en cours registredGameId
    public static LinkedList<String> messagesHistory = new LinkedList<>();


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

    
}
