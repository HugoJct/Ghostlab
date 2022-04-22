package main.java.gui;

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
        
        String ip = frame.getConnectionPanel().getIpField().getText();
        int port = Integer.parseInt(frame.getConnectionPanel().getPortField().getText());

        new Client(ip, port);

        if (Client.isConnected) {
            frame.getConnectionPanel().setBackground(new Color(178, 255, 102));
            frame.getConnectionPanel().getConnectionButton().setEnabled(false);
        } else {
            frame.getConnectionPanel().setBackground(new Color(255, 102, 102));
        }
    }
}
