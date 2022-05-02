package main.java.gui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

  private ConsolePanel consolePanel;
  private ConnectionPanel connectionPanel;
  private GamePanel gamePanel;
  private GameManagerPanel gameManagerPanel;
  private Tabs tabs;
  private OptionsPanel optionsPanel;

  private JButton help;
  private JButton exit;

  public Frame() {

    super();
    setSize(600, 600);
    setResizable(true);
    setTitle("Ghostlab");

    JPanel mainPanel = new JPanel();

    consolePanel = new ConsolePanel();
    connectionPanel = new ConnectionPanel();
    gamePanel = new GamePanel();
    gameManagerPanel = new GameManagerPanel();
    optionsPanel = new OptionsPanel();

    help = new JButton("?");
    exit = new JButton("EXIT");

    optionsPanel.add(help);
    optionsPanel.add(Box.createHorizontalGlue());
    optionsPanel.add(exit);

    tabs = new Tabs();

    tabs.setBounds(40, 20, 300, 300);
    tabs.add("connection", connectionPanel);
    tabs.add("game manager", gameManagerPanel);
    tabs.add("game", gamePanel);

    mainPanel.add(tabs);

    this.setJMenuBar(optionsPanel);
    this.add(consolePanel, BorderLayout.EAST);
    this.add(mainPanel, BorderLayout.WEST);
    this.pack();
    this.setVisible(true);

  }

  public void setGameManagerPanel(GameManagerPanel gp) {
    this.gameManagerPanel = gp;
  }

  public JButton getExitButton() {
    return exit;
  }
  public JButton getHelpButton() {
    return help;
  }

  public ConnectionPanel getConnectionPanel() {
    return connectionPanel;
  }
  public GameManagerPanel getGameManagerPanel() {
    return gameManagerPanel;
  }

}