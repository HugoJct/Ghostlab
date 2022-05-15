package main.java.console;

import java.util.HashMap;
import java.util.Scanner;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.commands.CommandDev;
import main.java.commands.CommandTCP;
import main.java.commands.dev.CommandDebug;
import main.java.commands.dev.CommandGameInfo;
import main.java.commands.dev.CommandHelp;
import main.java.commands.dev.CommandKill;
import main.java.commands.dev.CommandKillClient;
import main.java.commands.out.CommandAskTcpAvailableGames;
import main.java.commands.out.CommandAskTcpCreate;
import main.java.commands.out.CommandAskTcpJoin;
import main.java.commands.out.CommandAskTcpMapSize;
import main.java.commands.out.CommandAskTcpMoveDown;
import main.java.commands.out.CommandAskTcpMoveLeft;
import main.java.commands.out.CommandAskTcpMoveRight;
import main.java.commands.out.CommandAskTcpMoveUp;
import main.java.commands.out.CommandAskTcpPlayerGame;
import main.java.commands.out.CommandAskTcpStart;
import main.java.commands.out.CommandAskUnregister;

public class Console implements Runnable {
    private static ClientTCP clientTCP;

    private static HashMap<String, Command> commandList = new HashMap<>();

    public static void connectConsole(ClientTCP clientTcp) {
        clientTCP = clientTcp;

        // remplissage de la liste des commandes TCP fonctionnelles
        commandList.put("GAME?", new CommandAskTcpAvailableGames(clientTcp.getPrintWriter()));
        commandList.put("NEWPL", new CommandAskTcpCreate(clientTcp.getPrintWriter()));
        commandList.put("REGIS", new CommandAskTcpJoin(clientTcp.getPrintWriter()));
        commandList.put("SIZE?", new CommandAskTcpMapSize(clientTcp.getPrintWriter()));
        commandList.put("LIST?", new CommandAskTcpPlayerGame(clientTcp.getPrintWriter()));
        commandList.put("START", new CommandAskTcpStart(clientTcp.getPrintWriter()));
        commandList.put("UNREG", new CommandAskUnregister(clientTcp.getPrintWriter()));
        
        commandList.put("UPMOV", new CommandAskTcpMoveUp(clientTcp.getPrintWriter()));
        commandList.put("DOMOV", new CommandAskTcpMoveDown(clientTcp.getPrintWriter()));
        commandList.put("LEMOV", new CommandAskTcpMoveLeft(clientTcp.getPrintWriter()));
        commandList.put("RIMOV", new CommandAskTcpMoveRight(clientTcp.getPrintWriter()));
        
    }

    public static void createConsole() {
        // remplissage de la liste avec des commandes developpeur
        commandList.put("debug", new CommandDebug());
        commandList.put("help", new CommandHelp());
        commandList.put("kill", new CommandKill());
        commandList.put("killclient", new CommandKillClient());
        commandList.put("gameinfo", new CommandGameInfo());

        // remplissage de la liste des commandes tcp non fonctionnelles
        commandList.put("GAME?", new CommandAskTcpAvailableGames(null));
        commandList.put("NEWPL", new CommandAskTcpCreate(null));
        commandList.put("REGIS", new CommandAskTcpJoin(null));
        commandList.put("SIZE?", new CommandAskTcpMapSize(null));
        commandList.put("LIST?", new CommandAskTcpPlayerGame(null));
        commandList.put("START", new CommandAskTcpStart(null));
        commandList.put("UNREG", new CommandAskUnregister(null));

        new Thread(new Console()).start();
    }

    @Override
    public void run() {

        Scanner sc = new Scanner(System.in);
        String consoleMsg;
        layout();
        while(true) {

            /* 
            * on collecte l'input console a traiter
            */
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
    public static void useMessage(String strCommand) {
        
        if (strCommand.equals("")) {
            layout();
            return;
        }

        String[] args = breakCommand(strCommand);
        Command command = commandList.get(args[0]);

        if (command instanceof CommandTCP) {
            if (Client.isConnected) {
                ((CommandTCP) command).execute(clientTCP, args);
            } else {
                DebugLogger.print(DebugType.WARNING, "[ATTENTION/CONSOLE] : Vous n'êtes pas connecté à un serveur, impossible d'utiliser cette commande");
            }
        }

        else if (command instanceof CommandDev) {
            layout();
            ((CommandDev) command).execute(args);
        }

        else {
            DebugLogger.print(DebugType.CONFIRM, "UNKNOWN command");
        }
        
    }

    private static String[] breakCommand(String command) {
        return command.split(" ");
	}
    
}
