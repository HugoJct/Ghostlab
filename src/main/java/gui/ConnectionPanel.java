package main.java.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;

public class ConnectionPanel extends JPanel {
    
    public ConnectionPanel() {
        JLabel portLabel = new JLabel("Port : ");
        JLabel ipLabel = new JLabel("IP : ");
        JLabel playerLabel = new JLabel("player ID :");
    
        JTextField portField = new JTextField(5);
        JTextField ipField = new JTextField(15);
        JTextField playerField = new JTextField(8);
    
        JButton connectionButton = new JButton("Connect");
    
        GridLayout connectionLayout = new GridLayout(4, 2);

        this.setLayout(connectionLayout);
        this.add(portLabel);
        this.add(portField);
        this.add(ipLabel);
        this.add(ipField);
        this.add(playerLabel);
        this.add(playerField);
        this.add(connectionButton);
    }
}
