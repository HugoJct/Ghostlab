package main.java.game;

public class Ghost {
    private final int x;
    private final int y;

    public Ghost(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getPosX() {
        return x;
    }
    public int getPosY() {
        return y;
    }
}
