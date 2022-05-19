package main.java.gui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

import main.java.gui.game.Labyrinthe;
import main.java.gui.menu.Game;
import main.java.gui.menu.Menu;
import main.java.gui.menu.Options;
import main.java.gui.menu.Tabs;

public class Frame extends JFrame {

  private ConsolePanel consolePanel;
  private ConnectionPanel connectionPanel;
  private Game gamePanel;
  private GameManagerPanel gameManagerPanel;
  private Tabs tabs;
  private Options optionsPanel;
  private Menu interactivePanel;
  private Labyrinthe lab;

  private JButton help;
  private JButton exit;

  public Frame() {

    super();
    setSize(800, 600);
    setResizable(true);
    setTitle("Ghostlab");

    consolePanel = new ConsolePanel(400, 800);
    connectionPanel = new ConnectionPanel();
    gamePanel = new Game();
    gameManagerPanel = new GameManagerPanel();
    optionsPanel = new Options();
    interactivePanel = new Menu(400, 600);
    lab = null;

    help = new JButton("?");
    exit = new JButton("EXIT");

    optionsPanel.add(help);
    optionsPanel.add(Box.createHorizontalGlue());
    optionsPanel.add(exit);

    tabs = new Tabs(400, 600);

    tabs.setBounds(300, 300, 400, 600);
    tabs.add("connection", connectionPanel);
    tabs.add("game manager", gameManagerPanel);
    tabs.add("game", gamePanel);

    interactivePanel.add(tabs);

    this.setJMenuBar(optionsPanel);
    this.add(interactivePanel, BorderLayout.WEST);
    this.add(consolePanel, BorderLayout.EAST);
    this.pack();
    this.setVisible(true);

  }

  public void setGameManagerPanel(GameManagerPanel gp) {
    this.gameManagerPanel = gp;
  }

  public void createLab(int gameId) {
    lab = new Labyrinthe(gameId);
    gamePanel.add(lab);
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