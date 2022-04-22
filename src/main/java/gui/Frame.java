package main.java.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frame extends JFrame {

  public Frame() {

    super();
    setSize(600, 600);
    setResizable(true);
    setTitle("Ghostlab");

    JPanel mainPanel = new JPanel();

    ConsolePanel consolePanel = new ConsolePanel();
    ConnectionPanel connectionPanel = new ConnectionPanel();
    GamePanel gamePanel = new GamePanel();

    Tabs tabs = new Tabs();

    tabs.setBounds(40, 20, 300, 300);
    tabs.add("connection panel", connectionPanel);
    tabs.add("game panel", gamePanel);

    mainPanel.add(tabs);

    this.add(consolePanel, BorderLayout.EAST);
    this.add(mainPanel, BorderLayout.WEST);
    this.pack();
    this.setVisible(true);
  }

}