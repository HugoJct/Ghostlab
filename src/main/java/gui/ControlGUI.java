package main.java.gui;

import main.java.client.Client;

public class ControlGUI {
    private Frame frame;

    public ControlGUI(Frame frame) {
        this.frame = frame;
        frame.getConnectionPanel().getConnectionButton().addActionListener((event) -> connect());
    }

    public void connect() {
        new Client(frame.getConnectionPanel().getIpField().getText(), Integer.parseInt(frame.getConnectionPanel().getPortField().getText()));
    }
}
