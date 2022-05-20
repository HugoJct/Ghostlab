package main.java.gui.game;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Box {
    public static BufferedImage WALL, PATH, GHOST, PLAYER, VOID;

    public static void setTextures() {
        try {
            /* WALL = ImageIO.read(new File("wall.png"));
            PATH = ImageIO.read(new File("path.png"));
            GHOST = ImageIO.read(new File("ghost.png"));
            PLAYER = ImageIO.read(new File("player.png")); */
            VOID = ImageIO.read(new File(new Box().getClass().getResource("../../../../textures/VOID.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
