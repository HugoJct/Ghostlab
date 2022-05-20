package main.java.gui.game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;

import main.java.game.GameInfo;

import java.awt.GridLayout;

public class Labyrinthe extends JPanel {
    private JLabel[][] lab;
    private int nbrMoves;

    public Labyrinthe() {

        this.setLayout(new GridLayout(GameInfo.gameHeight, GameInfo.gameWidth));
        this.lab = new JLabel[GameInfo.gameHeight][GameInfo.gameWidth];
        this.nbrMoves = 0;

        for (int i = 0; i < GameInfo.gameHeight; i++) {
            for (int j = 0; j < GameInfo.gameWidth; j++) {
                JLabel img = new JLabel();
                this.add(img);
                lab[i][j] = img;
                img.setIcon(new ImageIcon(Box.VOID.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            }
        }
        lab[GameInfo.players.get(GameInfo.playerID).getPosX()][GameInfo.players.get(GameInfo.playerID).getPosY()].setIcon(new ImageIcon(Box.PLAYER.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
    }

    public void actualise() {
        int posX = GameInfo.players.get(GameInfo.playerID).getPosX();
        int posY = GameInfo.players.get(GameInfo.playerID).getPosY();
        int lastPosX = GameInfo.players.get(GameInfo.playerID).getLastPosX();
        int lastPosY = GameInfo.players.get(GameInfo.playerID).getLastPosY();
        int shiftingAsked = GameInfo.players.get(GameInfo.playerID).getShiftingAsked();

        System.out.println("posX : " + posX + " posY : " + posY + " lastPosX : " + lastPosX + " lastPosY : " + lastPosY + " shiftingAsked : " + shiftingAsked);

        if (GameInfo.nbrMoves != nbrMoves) {
            nbrMoves++;

            // S'est-on déplace en X ?
            if (posX != lastPosX) {

                // S'est-on déplacé vers la drwate ?
                if (lastPosX < posX) {

                    // A-t-on rencontré un mur à drwate ?
                    if (posX - lastPosX != shiftingAsked) {
                        lab[posY][posX+1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }

                    // Pavage des cases où on s'est déplacé
                    for (int i = lastPosX ; i <= posX ; i++) {
                        lab[posY][i].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                }

                // S'est-on déplacé vers la gôche ?
                else if (lastPosX > posX) {

                    // A-t-on rencontré un mur à gôche ?
                    if (lastPosX - posX != shiftingAsked) {
                        lab[posY][posX-1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }

                    // Pavage des cases où on s'est déplacé
                    for (int i = lastPosX ; i >= posX ; i--) {
                        lab[posY][i].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                }

                // la dernière position en X est maintenant la position courante
                GameInfo.players.get(GameInfo.playerID).setLastPosX(posX);
            }

            // S'est-on déplace en Y ?
            else if (posY != lastPosY) {

                // S'est-on déplacé vers le bas ?
                if (lastPosY < posY) {
                    if (posY - lastPosY != shiftingAsked) {
                        lab[posY+1][posX].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                    for (int i = lastPosY; i <= posY; i++) {
                        lab[i][posX].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                }

                // S'est-on déplacé vers le haut ?
                else if (lastPosY > posY) {
                    if (lastPosY - posY != shiftingAsked) {
                        lab[posY-1][posX].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                    for (int i = lastPosY; i >= posY; i--) {
                        lab[i][posX].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                }

                // la dernière position en Y est maintenant la position courante
                GameInfo.players.get(GameInfo.playerID).setLastPosY(posY);
            }

            // drwate
            else if (GameInfo.lastMoveDirection == 0) {
                lab[posY][posX+1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            }

            // gôche
            else if (GameInfo.lastMoveDirection == 1) {
                lab[posY][posX-1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            }

            // bas
            else if (GameInfo.lastMoveDirection == 2) {
                lab[posY+1][posX].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            }

            // haut
            else if (GameInfo.lastMoveDirection == 3) {
                lab[posY-1][posX].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            }

            // affichage du joueur à sa nouvelle position
            lab[posY][posX].setIcon(new ImageIcon(Box.PLAYER.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        }
    }
}
