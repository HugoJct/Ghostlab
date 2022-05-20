package main.java.gui.game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;

import main.java.game.GameInfo;

import java.awt.GridLayout;

public class Labyrinthe extends JPanel {
    private JLabel[][] lab;

    public Labyrinthe(int gameId) {

        this.setLayout(new GridLayout(GameInfo.gameHeight, GameInfo.gameWidth));

        for (int i = 0; i < GameInfo.gameHeight; i++) {
            for (int j = 0; j < GameInfo.gameWidth; j++) {
                JLabel img = new JLabel();
                this.add(img);
                lab[i][j] = img;
                img.setIcon(new ImageIcon(Box.VOID.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            }
        }
    }

    public void actualise() {
        int posX = GameInfo.players.get(GameInfo.playerID).getPosX();
        int posY = GameInfo.players.get(GameInfo.playerID).getPosY();
        int lastPosX = GameInfo.players.get(GameInfo.playerID).getLastPosX();
        int lastPosY = GameInfo.players.get(GameInfo.playerID).getLastPosY();
        int shiftingAsked = GameInfo.players.get(GameInfo.playerID).getShiftingAsked();

        if (posX != lastPosX) {
            if (lastPosX < posX) {
                if (posX - lastPosX != shiftingAsked) {
                    lab[posX+1][posY].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
                for (int i = lastPosX; i < posX; i++) {
                    lab[i][posY].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
            }
            else if (lastPosX > posX) {
                if (lastPosX - posX != shiftingAsked) {
                    lab[posX-1][posY].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
                for (int i = lastPosX; i > posX; i--) {
                    lab[i][posY].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
            }
            GameInfo.players.get(GameInfo.playerID).setLastPosX(posX);
        }

        if (posY != lastPosY) {
            if (lastPosY < posY) {
                if (posY - lastPosY != shiftingAsked) {
                    lab[posX][posY+1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
                for (int i = lastPosY; i < posY; i++) {
                    lab[posX][i].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
            }
            else if (lastPosY > posY) {
                if (lastPosY - posY != shiftingAsked) {
                    lab[posX][posY-1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
                for (int i = lastPosY; i > posY; i--) {
                    lab[posX][i].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
            }
            GameInfo.players.get(GameInfo.playerID).setLastPosY(posY);
        }

        lab[posX][posY].setIcon(new ImageIcon(Box.PLAYER.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
    }
}
