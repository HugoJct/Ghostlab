package main.java.view.gui;


import java.awt.BorderLayout;
import java.awt.Dimension;

import main.java.exceptions.GhostlabException;
import main.java.view.gui.menu.Game;

public class GamePanel extends JPanel {
    private Game game;

    public GamePanel(Frame frame) {

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		
		try {
			game = new Game(frame);
			
			add(game);
			
			game.setVisible(true);
			
		} catch (GhostlabException e) {
			e.printStackTrace();
			//TODO: so we got a error hum..
			System.exit(0);
		}
		setVisible(true);
		setFocusable(true);
		
	}
}
