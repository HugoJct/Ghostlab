package main.java.view.gui.menu;

import javax.swing.JMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Options extends JMenu implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        super("Options");
        
    }
    
}
