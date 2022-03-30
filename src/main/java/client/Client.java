package main.java.client;

import java.io.IOException;

import main.java.console.Console;
import main.java.console.DebugLogger;

public abstract class Client implements Runnable {

    public static void main(String[] args) {
        if (args.length > 2) {
            System.out.println("ATTENTION : " + args.length + " paramètres donnés alors que seulement 2 attendus... Erreurs potentielles (voir README.md)");
        }
        else if (args.length < 2) {
            System.out.println("ERREUR : nombre de paramètres minimaux non atteint... arg1 -> ip , arg2 -> port (voir README.md)");
            System.exit(1);
        }
        /* Thread t = new Thread(new Runnable() { 
            public void run() {

            }
        }); */
        
        DebugLogger.setTypeMap();
        ClientTCP clientTCP = new ClientTCP(args[0], Integer.parseInt(args[1]));
        new Console(clientTCP).start();
        clientTCP.start();

        
    }

}