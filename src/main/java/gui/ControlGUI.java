package main.java.gui;

import main.java.GameInfo;
import main.java.client.Client;
import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

import java.awt.Color;

public class ControlGUI {
    private Frame frame;
    private Client client;

    public ControlGUI(Frame frame) {
        this.frame = frame;
        frame.getConnectionPanel().getConnectionButton().addActionListener((event) -> connect());
        frame.getConnectionPanel().getDisconectionButton().addActionListener((event) -> disconnect());
        frame.getGameManagerPanel().getJoinButton().addActionListener((event) -> joinGame());
        frame.getGameManagerPanel().getNewGameButton().addActionListener((event) -> createNewGame());
        frame.getGameManagerPanel().getRefreshButton().addActionListener((event) -> refreshGamesList());
        frame.getExitButton().addActionListener((event) -> exit());
        frame.getHelpButton().addActionListener((event) -> help());

        frame.getConnectionPanel().getDisconectionButton().addChangeListener((event) -> actualise());
    }

    private void exit() {
        System.out.println("");
        Console.useMessage("kill"); 
    }
    private void help() {
        System.out.println("");
        Console.useMessage("help"); 
    }

    private void joinGame() {
        int id = frame.getGameManagerPanel().getSelectedButtonID();
        if (id != -1) {
            Console.useMessage("REGIS " + id);
        } else {
            DebugLogger.print(DebugType.ERROR, "GUI : aucun bouton sélectionné");
        }
    }

    private void createNewGame() {
        Console.useMessage("NEWPL");
    }

    private void refreshGamesList() {

        Console.useMessage("GAME?");

    }

    public void actualise() {
        if (Client.isConnected) {
            frame.getConnectionPanel().getConnectionButton().setEnabled(false);
            frame.getConnectionPanel().getDisconectionButton().setEnabled(true);
            frame.getGameManagerPanel().listGames();
        } else {
            frame.getConnectionPanel().getConnectionButton().setEnabled(true);
            frame.getConnectionPanel().getDisconectionButton().setEnabled(false);
            frame.getGameManagerPanel().getNewGameButton().setEnabled(false);
            frame.getGameManagerPanel().getNewGameButton().setEnabled(false);
            frame.getGameManagerPanel().freeGamesList();
            
        }
        
        frame.repaint();
    }

    public void connect() {

        if (frame.getConnectionPanel().getIpField().getText().isEmpty() || frame.getConnectionPanel().getPortField().getText().isEmpty()) {
            frame.getConnectionPanel().setBackground(new Color(255, 102, 102));
            return;
        }

        client = new Client(frame.getConnectionPanel().getIpField().getText(), Integer.parseInt(frame.getConnectionPanel().getPortField().getText()), this);

        if (frame.getConnectionPanel().getplayerField().getText().isEmpty()) {
            GameInfo.playerID = "unknUser";
        } else {
            String playerId = "";

            for(int i = 0 ; i<8 ; i++) {
                if (i > frame.getConnectionPanel().getplayerField().getText().length() - 1) {
                    playerId += "x";
                } else {
                    playerId += frame.getConnectionPanel().getplayerField().getText().charAt(i);
                }
            }
            GameInfo.playerID = playerId;
        }

        if (Client.isConnected) {
            frame.getConnectionPanel().setBackground(new Color(178, 255, 102));
            frame.getConnectionPanel().getConnectionButton().setEnabled(false);
            frame.getConnectionPanel().getDisconectionButton().setEnabled(true);
            frame.getGameManagerPanel().getNewGameButton().setEnabled(true);
            frame.getGameManagerPanel().listGames();
            frame.repaint();
            Console.connectConsole(client.getClientTCP());
        } else {
            frame.getConnectionPanel().setBackground(new Color(255, 102, 102));
        }
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void disconnect() {
        client.getClientTCP().closeSocket();
        actualise();
    }
}
