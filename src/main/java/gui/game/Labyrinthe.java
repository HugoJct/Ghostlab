package main.java.gui.game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;
import java.util.HashMap;

import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.game.Ghost;

import java.awt.GridLayout;

public class Labyrinthe extends JPanel {
    private JLabel[][] lab;
    private int nbrMoves;
    private int nbrGhostsShifting;
    private HashMap<Ghost, ImageIcon> lastBox;

    public Labyrinthe() {

        this.setLayout(new GridLayout(GameInfo.gameHeight, GameInfo.gameWidth));
        this.lab = new JLabel[GameInfo.gameWidth][GameInfo.gameHeight];
        this.nbrMoves = 0;
        this.nbrGhostsShifting = 0;

        this.lastBox = new HashMap<>();

        for (int i = 0; i < GameInfo.gameHeight; i++) {
            for (int j = 0; j < GameInfo.gameWidth; j++) {
                JLabel img = new JLabel();
                this.add(img);
                lab[j][i] = img;
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

        DebugLogger.print(DebugType.CONFIRM, "posX : " + posX + " posY : " + posY + " lastPosX : " + lastPosX + " lastPosY : " + lastPosY + " shiftingAsked : " + shiftingAsked);

        for (HashMap.Entry<Ghost, ImageIcon> g : lastBox.entrySet()) {
                
                lab[g.getKey().getPosX()][g.getKey().getPosY()].setIcon(g.getValue());
                System.out.println("posX : " + g.getKey().getPosX() + " posY : " + g.getKey().getPosY());
                lastBox.remove(g.getKey());

        }

        lastBox.clear();
        

        for (int i = nbrGhostsShifting ; i < GameInfo.ghosts.size() ; i++) {
                int ghostPosX = GameInfo.ghosts.get(i).getPosX();
                int ghostPosY = GameInfo.ghosts.get(i).getPosY();
                lastBox.put(GameInfo.ghosts.get(i), (ImageIcon) lab[ghostPosX][ghostPosY].getIcon());
                DebugLogger.print(DebugType.CONFIRM, "ghostPosX : " + ghostPosX + " ghostPosY : " + ghostPosY);
                lab[ghostPosX][ghostPosY].setIcon(new ImageIcon(Box.GHOST.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                nbrGhostsShifting++;
        }

        if (GameInfo.nbrMoves != nbrMoves) {
            nbrMoves++;

            // S'est-on déplace en X ?
            if (posX != lastPosX) {

                // S'est-on déplacé vers la drwate ?
                if (lastPosX < posX) {

                    // A-t-on rencontré un mur à drwate ?
                    if (posX - lastPosX != shiftingAsked) {
                        if (posX+1 < GameInfo.gameWidth) {
                            lab[posX+1][posY].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                        }
                    }

                    // Pavage des cases où on s'est déplacé
                    for (int i = lastPosX ; i <= posX ; i++) {
                        lab[i][posY].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                }

                // S'est-on déplacé vers la gôche ?
                else if (lastPosX > posX) {

                    // A-t-on rencontré un mur à gôche ?
                    if (lastPosX - posX != shiftingAsked) {
                        if (posX-1 > 0) {
                            lab[posX-1][posY].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                        }
                    }

                    // Pavage des cases où on s'est déplacé
                    for (int i = lastPosX ; i >= posX ; i--) {
                        lab[i][posY].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
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
                        if (posY+1 < GameInfo.gameHeight) {
                            lab[posX][posY+1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                        }
                        
                    }
                    for (int i = lastPosY; i <= posY; i++) {
                        lab[posX][i].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                }

                // S'est-on déplacé vers le haut ?
                else if (lastPosY > posY) {
                    if (lastPosY - posY != shiftingAsked) {
                        if (posY-1 > 0) {
                            lab[posX][posY-1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                        }
                    }
                    for (int i = lastPosY; i >= posY; i--) {
                        lab[posX][i].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                }

                // la dernière position en Y est maintenant la position courante
                GameInfo.players.get(GameInfo.playerID).setLastPosY(posY);
            }

            // drwate
            else if (GameInfo.lastMoveDirection == 0) {
                if (posX+1 < GameInfo.gameWidth) {
                    lab[posX+1][posY].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
            }

            // gôche
            else if (GameInfo.lastMoveDirection == 1) {
                if (posX-1 > 0) {
                    lab[posX-1][posY].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
            }

            // bas
            else if (GameInfo.lastMoveDirection == 2) {
                if (posY+1 < GameInfo.gameHeight) {
                    lab[posX][posY+1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
            }

            // haut
            else if (GameInfo.lastMoveDirection == 3) {
                if (posY-1 > 0) {
                    lab[posX][posY-1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
            }

            // affichage du joueur à sa nouvelle position
            lab[posX][posY].setIcon(new ImageIcon(Box.PLAYER.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        }

    }

}
