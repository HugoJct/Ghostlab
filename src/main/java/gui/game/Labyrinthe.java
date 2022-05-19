package main.java.gui.game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.game.GameInfo;

import java.awt.GridLayout;

public class Labyrinthe extends JPanel {

    public Labyrinthe(int gameId) {

        this.setLayout(new GridLayout(GameInfo.gameHeight, GameInfo.gameWidth));

        for (int i = 0; i < GameInfo.gameHeight; i++) {
            for (int j = 0; j < GameInfo.gameWidth; j++) {
                this.add(new JLabel(new ImageIcon(Box.VOID)));
            }
        }
    }
}
