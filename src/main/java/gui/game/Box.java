package main.java.gui.game;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Box {
    public static BufferedImage WALL, PATH, GHOST, PLAYER, VOID;

    public static void setTextures() {
        try {
            WALL = ImageIO.read(new File("../../../../textures/WALL.png"));
            PATH = ImageIO.read(new File("../../../../textures/PATH.png"));
            GHOST = ImageIO.read(new File("../../../../textures/GHOST.png"));
            PLAYER = ImageIO.read(new File("../../../../textures/PLAYER.png"));
            VOID = ImageIO.read(new File(new Box().getClass().getResource("../../../../textures/VOID.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
