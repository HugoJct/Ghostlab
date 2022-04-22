package main.java.gui;

import main.java.GameInfo;
import main.java.client.Client;
import java.awt.Color;

import javax.swing.JTextField;

public class ControlGUI {
    private Frame frame;

    public ControlGUI(Frame frame) {
        this.frame = frame;
        frame.getConnectionPanel().getConnectionButton().addActionListener((event) -> connect());
    }

    public void connect() {

        if (frame.getConnectionPanel().getIpField().getText().isEmpty() || frame.getConnectionPanel().getPortField().getText().isEmpty()) {
            frame.getConnectionPanel().setBackground(new Color(255, 102, 102));
            return;
        }

        new Client(frame.getConnectionPanel().getIpField().getText(), Integer.parseInt(frame.getConnectionPanel().getPortField().getText()));

        String playerId = "";

        for(int i = 0 ; i<8 ; i++) {
            if (i > frame.getConnectionPanel().playerIdField.getText().length() - 1) {
                playerId += "x";
            } else {
                playerId += frame.getConnectionPanel().playerIdField.getText().charAt(i);
            }
        }

        System.out.println(playerId);

        GameInfo.playerID = playerId;

        if (Client.isConnected) {
            frame.getConnectionPanel().setBackground(new Color(178, 255, 102));
            frame.getConnectionPanel().getConnectionButton().setEnabled(false);
            frame.getConnectionPanel().getDisconectionButton().setEnabled(true);
        } else {
            frame.getConnectionPanel().setBackground(new Color(255, 102, 102));
        }
    }
}
