package main.java.gui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame {

  private ConsolePanel consolePanel;
  private ConnectionPanel connectionPanel;
  private GamePanel gamePanel;
  private GameManagerPanel gameManagerPanel;
  private Tabs tabs;
  private OptionsMenu optionsPanel;
  private InteractivePanel interactivePanel;

  private JButton help;
  private JButton exit;

  public Frame() {

    super();
    setSize(1000, 600);
    setResizable(true);
    setTitle("Ghostlab");

    consolePanel = new ConsolePanel();
    connectionPanel = new ConnectionPanel();
    gamePanel = new GamePanel();
    gameManagerPanel = new GameManagerPanel();
    optionsPanel = new OptionsMenu();
    interactivePanel = new InteractivePanel(400, 600);

    help = new JButton("?");
    exit = new JButton("EXIT");

    optionsPanel.add(help);
    optionsPanel.add(Box.createHorizontalGlue());
    optionsPanel.add(exit);

    tabs = new Tabs();

    tabs.setBounds(40, 20, 300, 500);
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