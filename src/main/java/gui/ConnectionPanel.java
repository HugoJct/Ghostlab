package main.java.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.client.Client;

import java.awt.GridLayout;

public class ConnectionPanel extends JPanel {
    JTextField portField;
    JTextField ipField;
    JTextField playerField;

    JButton connectionButton = new JButton("Connect");

    public ConnectionPanel() {
        JLabel portLabel = new JLabel("Port : ");
        JLabel ipLabel = new JLabel("IP : ");
        JLabel playerLabel = new JLabel("player ID :");
    
        portField = new JTextField(5);
        ipField = new JTextField(15);
        playerField = new JTextField(8);
    
        connectionButton = new JButton("Connect");
    
        GridLayout connectionLayout = new GridLayout(4, 2);

        this.setLayout(connectionLayout);
        this.add(ipLabel);
        this.add(ipField);
        this.add(portLabel);
        this.add(portField);
        this.add(playerLabel);
        this.add(playerField);
        this.add(connectionButton);

    }

    public JTextField getPortField() {
        return portField;
    }
    public JTextField getIpField() {
        return ipField;
    }
    public JTextField getplayerField() {
        return playerField;
    }

    public JButton getConnectionButton() {
        return connectionButton;
    }
}
