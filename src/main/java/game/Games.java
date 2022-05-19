package main.java.game;

public class Games {
    private final int height;
    private final int width;
    private final int nbrPlayers;

    public Games(int nbrPlayers, int height, int width) {
        this.height = height;
        this.width = width;
        this.nbrPlayers = nbrPlayers;
    }
    public Games(int nbrPlayers) {
        this.nbrPlayers = nbrPlayers;
        this.height = -1;
        this.width = -1;
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int getNbrPlayers() {
        return nbrPlayers;
    }
}
