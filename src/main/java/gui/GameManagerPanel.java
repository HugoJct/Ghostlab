package main.java.gui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import main.java.GameInfo;
import main.java.console.Console;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class GameManagerPanel extends JPanel {
    private LinkedList<JRadioButton> gameListSelector;
    private LinkedList<JButton> gameListRefreshButtons;
    private JButton joinButton;
    private JButton refreshButton;
    private JButton createButton;
    private JButton startButton;
    private JButton leaveButton;
    private ButtonGroup buttonGroup;

    public GameManagerPanel() {
        gameListSelector = new LinkedList<>();
        gameListRefreshButtons = new LinkedList<>();
        joinButton = new JButton("join game");
        createButton = new JButton("new game");
        refreshButton = new JButton("refresh list");
        startButton = new JButton("start game");
        leaveButton = new JButton("leave game");
        buttonGroup = new ButtonGroup();

        createButton.setEnabled(false);
        startButton.setEnabled(false);
        leaveButton.setEnabled(false);

        GridLayout gameManagerLayout = new GridLayout(2, 2);
        this.setLayout(gameManagerLayout);
        this.add(joinButton);
        this.add(createButton);
        this.add(refreshButton);
        this.add(startButton);
        this.add(leaveButton);
    }

    public void listGames() {

        freeGamesList();
        GridLayout gameManagerLayout = new GridLayout(GameInfo.nbrGames+2, 3);
        this.setLayout(gameManagerLayout);

        for (int i = gameListSelector.size() ; i < GameInfo.gameIdNbrPlayers.size() ; i++) {
            JRadioButton selector = new JRadioButton();
            JButton button = new JButton("refresh game");
            gameListSelector.add(selector);
            gameListRefreshButtons.add(button);
            buttonGroup.add(selector);
            this.add(new JLabel("game n°" + i + ", " + GameInfo.gameIdNbrPlayers.get(i) + " joueurs inscrits"));
            this.add(selector);
            this.add(button);
            int m = i;
            button.addActionListener(new ActionListener(){
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    Console.useMessage("LIST? " + m);
                }
            });
        }
        this.add(joinButton);
        this.add(createButton);
        this.add(refreshButton);
        this.add(startButton);
        this.add(leaveButton);

        startButton.setEnabled(false);
        leaveButton.setEnabled(false);
    }

    public void freeGamesList() {
        gameListSelector.clear();
        gameListRefreshButtons.clear();
        this.removeAll();

        GridLayout gameManagerLayout = new GridLayout(2, 2);
        this.setLayout(gameManagerLayout);
        this.add(joinButton);
        this.add(createButton);
        this.add(refreshButton);
        this.add(startButton);
        this.add(leaveButton);
        startButton.setEnabled(false);
        leaveButton.setEnabled(false);
    }

    public JButton getJoinButton() {
        return joinButton;
    }
    public JButton getNewGameButton() {
        return createButton;
    }
    public JButton getRefreshButton() {
        return refreshButton;
    }
    public JButton getStartButton() {
        return startButton;
    }
    public JButton getLeaveButton() {
        return leaveButton;
    }
    public int getSelectedButtonID() {
        for (int i = 0 ; i < gameListSelector.size() ; i++) {
            if (gameListSelector.get(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }

}
