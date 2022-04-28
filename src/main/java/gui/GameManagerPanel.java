package main.java.gui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import main.java.GameInfo;

import java.awt.GridLayout;
import java.util.LinkedList;

public class GameManagerPanel extends JPanel {
    private LinkedList<JRadioButton> gameListButtons;
    private JButton joinButton;
    private JButton refreshButton;
    private JButton createButton;
    private ButtonGroup buttonGroup;

    public GameManagerPanel() {
        gameListButtons = new LinkedList<>();
        joinButton = new JButton("join game");
        createButton = new JButton("new game");
        refreshButton = new JButton("refresh list");
        buttonGroup = new ButtonGroup();

        createButton.setEnabled(false);

        GridLayout gameManagerLayout = new GridLayout(2, 2);
        this.setLayout(gameManagerLayout);
        this.add(joinButton);
        this.add(createButton);
        this.add(refreshButton);
    }

    public void listGames() {

        if (gameListButtons.size() < GameInfo.nbrGames) {
            GridLayout gameManagerLayout = new GridLayout(GameInfo.nbrGames+2, 2);
            this.setLayout(gameManagerLayout);
    
            for (int i = gameListButtons.size() ; i < GameInfo.gameIdNbrPlayers.size() ; i++) {
                JRadioButton b = new JRadioButton();
                gameListButtons.add(b);
                buttonGroup.add(b);
                this.add(new JLabel("game n°" + i + ", " + GameInfo.gameIdNbrPlayers.get(i) + " joueurs inscrits"));
                this.add(b);
            }
            this.add(joinButton);
            this.add(createButton);
            this.add(refreshButton);
        }

    }

    public void freeGamesList() {
        gameListButtons.clear();
        this.removeAll();

        createButton.setEnabled(false);

        GridLayout gameManagerLayout = new GridLayout(2, 2);
        this.setLayout(gameManagerLayout);
        this.add(joinButton);
        this.add(createButton);
        this.add(refreshButton);
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
    public int getSelectedButtonID() {
        for (int i = 0 ; i < gameListButtons.size() ; i++) {
            if (gameListButtons.get(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }

}
