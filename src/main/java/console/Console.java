package main.java.console;

import java.util.HashMap;
import java.util.Scanner;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.commands.dev.CommandDebug;
import main.java.commands.dev.CommandGameInfo;
import main.java.commands.dev.CommandHelp;
import main.java.commands.dev.CommandKill;
import main.java.commands.out.CommandAskTcpAvailableGames;
import main.java.commands.out.CommandAskTcpCreate;
import main.java.commands.out.CommandAskTcpJoin;
import main.java.commands.out.CommandAskTcpMapSize;
import main.java.commands.out.CommandAskTcpPlayerGame;
import main.java.commands.out.CommandAskTcpStart;
import main.java.commands.out.CommandAskUnregister;

public class Console extends Thread {
    private ClientTCP clientTCP;

    private HashMap<String,CommandTCP> commandAskList = new HashMap<String,CommandTCP>();
    private HashMap<String, CommandTCP> commandDev = new HashMap<String, CommandTCP>();

    public Console(ClientTCP clientTcp) {
        this.clientTCP = clientTcp;

        // remplissage de la liste avec des commandes developpeur
        commandDev.put("debug", new CommandDebug(clientTcp.getPrintWriter()));
        commandDev.put("help", new CommandHelp(clientTcp.getPrintWriter()));
        commandDev.put("kill", new CommandKill(clientTcp.getPrintWriter()));
        commandDev.put("gameinfo", new CommandGameInfo(clientTcp.getPrintWriter()));

        // remplissage de la liste des commandes envoyables au serveur 
        commandAskList.put("GAME?", new CommandAskTcpAvailableGames(clientTcp.getPrintWriter()));
        commandAskList.put("NEWPL", new CommandAskTcpCreate(clientTcp.getPrintWriter()));
        commandAskList.put("REGIS", new CommandAskTcpJoin(clientTcp.getPrintWriter()));
        commandAskList.put("SIZE?", new CommandAskTcpMapSize(clientTcp.getPrintWriter()));
        commandAskList.put("LIST?", new CommandAskTcpPlayerGame(clientTcp.getPrintWriter()));
        commandAskList.put("START", new CommandAskTcpStart(clientTcp.getPrintWriter()));
        commandAskList.put("UNREG", new CommandAskUnregister(clientTcp.getPrintWriter()));
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String consoleMsg;
        /* 
         * tant que le client est connecté au serveur 
         * on collecte l'input console a traiter
         */
        while(clientTCP.isConnected()) {
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
    private void useMessage(String strCommand) {
        String[] args = breakCommand(strCommand);
        CommandTCP command = commandAskList.get(args[0]);
        if (command != null) {
            command.execute(clientTCP, args);
        }
        else {
            command = commandDev.get(args[0]);
            layout();
            if (command != null) {
                command.execute(clientTCP, args);
            } else {
                DebugLogger.print(DebugType.CONFIRM, "UNKNOWN command");
            }
        }
    }

    private String[] breakCommand(String command) {
        return command.split(" ");
	}
    
}
