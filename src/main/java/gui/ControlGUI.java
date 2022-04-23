package main.java.gui;

import main.java.GameInfo;
import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.client.ClientUDP;
import main.java.console.Console;

import java.awt.Color;

import javax.swing.JTextField;

public class ControlGUI {
    private Frame frame;
    private Client client;

    public ControlGUI(Frame frame) {
        this.frame = frame;
        frame.getConnectionPanel().getConnectionButton().addActionListener((event) -> connect());
        frame.getConnectionPanel().getDisconectionButton().addActionListener((event) -> disconnect());
        frame.getConnectionPanel().getDisconectionButton().addChangeListener((event) -> actualise());
        frame.getConnectionPanel().getConnectionButton().addChangeListener((event) -> actualise());
    }

    public void actualise() {
        if (Client.isConnected) {
            frame.getConnectionPanel().getConnectionButton().setEnabled(false);
            frame.getConnectionPanel().getDisconectionButton().setEnabled(true);
        } else {
            frame.getConnectionPanel().getConnectionButton().setEnabled(true);
            frame.getConnectionPanel().getDisconectionButton().setEnabled(false);
        }
    }

    public void connect() {

        if (frame.getConnectionPanel().getIpField().getText().isEmpty() || frame.getConnectionPanel().getPortField().getText().isEmpty()) {
            frame.getConnectionPanel().setBackground(new Color(255, 102, 102));
            return;
        }

        client = new Client(frame.getConnectionPanel().getIpField().getText(), Integer.parseInt(frame.getConnectionPanel().getPortField().getText()));

        if (frame.getConnectionPanel().playerIdField.getText().isEmpty()) {
            GameInfo.playerID = "unknUser";
        } else {
            String playerId = "";

            for(int i = 0 ; i<8 ; i++) {
                if (i > frame.getConnectionPanel().playerIdField.getText().length() - 1) {
                    playerId += "x";
                } else {
                    playerId += frame.getConnectionPanel().playerIdField.getText().charAt(i);
                }
            }
            GameInfo.playerID = playerId;
        }

        if (Client.isConnected) {
            frame.getConnectionPanel().setBackground(new Color(178, 255, 102));
            frame.getConnectionPanel().getConnectionButton().setEnabled(false);
            frame.getConnectionPanel().getDisconectionButton().setEnabled(true);
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
        frame.getConnectionPanel().getConnectionButton().setEnabled(true);
        frame.getConnectionPanel().getDisconectionButton().setEnabled(false);
    }
}
