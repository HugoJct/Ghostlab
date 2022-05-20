package main.java.gui.game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;

import main.java.game.GameInfo;

import java.awt.GridLayout;

public class Labyrinthe extends JPanel {

    public Labyrinthe(int gameId) {

        this.setLayout(new GridLayout(GameInfo.gameHeight, GameInfo.gameWidth));

        for (int i = 0; i < GameInfo.gameHeight; i++) {
            for (int j = 0; j < GameInfo.gameWidth; j++) {
                JLabel img = new JLabel();
                this.add(img);
                img.setIcon(new ImageIcon(Box.VOID.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            }
        }
    }
}
