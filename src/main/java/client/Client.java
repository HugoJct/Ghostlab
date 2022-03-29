package main.java.client;

import main.java.console.Console;

public abstract class Client {

    public static void main(String[] args) {
        if (args.length == 2) {
            new Console(new ClientTCP(args[0], Integer.parseInt(args[1])));
        }
        else if (args.length > 2) {
            System.out.println("ATTENTION : " + args.length + " paramètres donnés alors que seulement 2 attendus... Erreurs potentielles (voir README.md)");
            new Console(new ClientTCP(args[0], Integer.parseInt(args[2])));
        }
        else {
            System.out.println("ERREUR : nombre de paramètres minimaux non atteint... arg1 -> ip , arg2 -> port (voir README.md)");
            System.exit(1);
        }
        
    }

}