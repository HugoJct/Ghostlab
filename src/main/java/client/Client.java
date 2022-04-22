package main.java.client;

import java.io.IOException;

import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.gui.ConsoleGUI;
import main.java.gui.Frame;

public class Client {
    private ClientTCP clientTCP;
    private ClientUDP clientUDP;
    private Console console;

    public static boolean isConnected = false;

    public Client(String ip, int port) {
        DebugLogger.setTypeMap();
        
        this.clientTCP = new ClientTCP(ip, port);
        this.clientUDP = new ClientUDP(ip);
        this.console = new Console(clientTCP);
        
        Client.isConnected = true;

        this.console.start();
        this.clientTCP.start();
        this.clientUDP.start();
    }
    public static void main(String[] args) {
        if (args.length > 2) {
            System.out.println("ATTENTION : " + args.length + " paramètres donnés alors que seulement 2 attendus... Erreurs potentielles (voir README.md)");
        }
        else if (args.length < 2) {
            System.out.println("Paramètres de connection non précisés... Attente des informations données à l'interface");
            new Frame();
        } else {
            new Frame();
            new Client(args[0], Integer.parseInt(args[1]));
        }
        
    }

}