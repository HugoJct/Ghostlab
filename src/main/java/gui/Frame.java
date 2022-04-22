package main.java.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Frame extends JFrame {

  public Frame() {

    super();
    setSize(600, 600);
    setResizable(false);
    setTitle("Ghostlab");

    JPanel consolePanel = new JPanel();
    JPanel gamePanel = new JPanel();

    JLabel gameTitle = new JLabel("LE JEU OUI OUI");

    ConsoleGUI console = new ConsoleGUI();

    consolePanel.add(console.getScrollPane());

    gamePanel.add(gameTitle);

    this.add(consolePanel, BorderLayout.EAST);
    this.add(gamePanel, BorderLayout.WEST);
    this.pack();
    this.setVisible(true);
  }

}