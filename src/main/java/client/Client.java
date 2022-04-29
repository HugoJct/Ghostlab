package main.java.client;

import main.java.GameInfo;
import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.gui.ControlGUI;
import main.java.gui.Frame;

public class Client {
    private ClientTCP clientTCP;
    private ClientUDP clientUDP;
    private static Console console;

    public static boolean isConnected = false;

    public Client(String ip, int port, ControlGUI gui) {
        this.clientTCP = new ClientTCP(ip, port, gui);
        this.clientUDP = new ClientUDP(ip);
        Console.connectConsole(clientTCP);
        
        if (ClientTCP.clientTCPCreated && ClientUDP.clientUDPCreated) {
            Client.isConnected = true;
            this.clientTCP.start();
            this.clientUDP.start();
        }
    }

    public static void main(String[] args) {

        DebugLogger.setTypeMap();
        Client client;

        Console.createConsole();

        if (args.length > 3) {
            System.out.println("ATTENTION : " + args.length + " paramètres donnés alors que seulement 3 max attendus... Erreurs potentielles (voir README.md)");
        }
        else if (args.length < 3) {
            System.out.println("Paramètres de connection non précisés... Attente des informations données à l'interface");
            new ControlGUI(new Frame());
        } else {
            Frame frame = new Frame();
            ControlGUI gui = new ControlGUI(frame);
            client = new Client(args[0], Integer.parseInt(args[1]), gui);
            Console.connectConsole(client.clientTCP);
            
            if (args[2].length() == 8) {
                GameInfo.playerID = args[2];
            } else {
                GameInfo.playerID = "unknUser";
            }

            if (isConnected) {
                frame.getConnectionPanel().getConnectionButton().setEnabled(false);
                frame.getConnectionPanel().getDisconectionButton().setEnabled(true);
            }
        }
        
    }

    public ClientTCP getClientTCP() {
        return this.clientTCP;
    }
    public Console getConsole() {
        return this.console;
    }

}