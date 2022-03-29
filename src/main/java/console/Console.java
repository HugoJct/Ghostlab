package main.java.console;

import java.util.HashMap;
import java.util.Scanner;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.commands.dev.CommandDebug;
import main.java.commands.dev.CommandHelp;
import main.java.commands.dev.CommandKill;
import main.java.commands.out.CommandAskAvailableGames;
import main.java.commands.out.CommandAskCreate;
import main.java.commands.out.CommandAskJoin;
import main.java.commands.out.CommandAskMapSize;
import main.java.commands.out.CommandAskPlayerGame;
import main.java.commands.out.CommandAskStart;
import main.java.commands.out.CommandAskUnregister;

public class Console implements Runnable {
    private ClientTCP clientTCP;

    private HashMap<String,Command> commandAskList = new HashMap<String,Command>();
    private HashMap<String, Command> commandDev = new HashMap<String, Command>();

    public Console(ClientTCP clientTcp) {
        this.clientTCP = clientTcp;

        // remplissage de la liste avec des commandes developpeur
        commandDev.put("debug", new CommandDebug(clientTcp.getpPrintWriter()));
        commandDev.put("help", new CommandHelp(clientTcp.getpPrintWriter()));
        commandDev.put("kill", new CommandKill(clientTcp.getpPrintWriter()));


        // remplissage de la liste des commandes envoyables au serveur 
        commandAskList.put("GAME?", new CommandAskAvailableGames(clientTcp.getpPrintWriter()));
        commandAskList.put("NEWPL", new CommandAskCreate(clientTcp.getpPrintWriter()));
        commandAskList.put("REGIS", new CommandAskJoin(clientTcp.getpPrintWriter()));
        commandAskList.put("SIZE?", new CommandAskMapSize(clientTcp.getpPrintWriter()));
        commandAskList.put("LIST?", new CommandAskPlayerGame(clientTcp.getpPrintWriter()));
        commandAskList.put("START", new CommandAskStart(clientTcp.getpPrintWriter()));
        commandAskList.put("UNREG", new CommandAskUnregister(clientTcp.getpPrintWriter()));
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String consoleMsg;
        DebugLogger.setTypeMap();
        
        /* 
         * tant que le client est connecté au serveur 
         * on collecte l'input console a traiter
         */
        while(clientTCP.isConnected()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            layout();
            consoleMsg = sc.nextLine();
            useMessage(consoleMsg);
        } 
    }


    public static void layout() {
        System.out.print("\u001B[31m");
        System.out.print("ghostlab_debug> ");
        System.out.print("\u001B[37m");
    }


    // parsing de la commande et exécution de la fonction associée
    private void useMessage(String command) {
        String[] args = breakCommand(command);
        for (String str : commandAskList.keySet()) {
            commandAskList.get(str).execute(clientTCP, args);
            break;
        }
    }

    private String[] breakCommand(String command) {
        return command.split(" ");
	}
    
}
