package main.java.game;

public class Player {
    private int score;
    private int posX;
    private int posY;

    public Player(int score, int posX, int posY) {
        this.score = score;
        this.posX = posX;
        this.posY = posY;
    }

    public Player(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.score = 0;
    }

    public Player() {
        this.score = 0;
        this.posX = -1;
        this.posY = -1;
    }

    public int getScore() {
        return score;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
}
