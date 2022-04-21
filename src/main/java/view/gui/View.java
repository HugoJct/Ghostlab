package main.java.view.gui;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
//from   ww  w .jav  a2  s . c  o m
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class View extends JFrame {

  public View() {

    super();
    setSize(600, 600);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Ghostlab");

    ConsoleGUI console = new ConsoleGUI();

    this.add(console.getScrollPane());
    this.pack();
    this.setVisible(true);
  }

}