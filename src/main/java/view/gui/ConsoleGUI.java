package main.java.view.gui;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsoleGUI {
    private static JScrollPane scrollPane;
    private static JTextArea textArea = new JTextArea(24, 80);

    public ConsoleGUI() {
        
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        scrollPane = new JScrollPane(textArea);
        
        printLaunchMessage();
    }

    public static void printLaunchMessage() {
        textArea.append("################## INTERFACE FEED-BACK GHOSTLAB ##################\n");
        textArea.append("#                                                                #\n");
        textArea.append("#      ATTENTION : ceci n'est pas un terminal de commandes !     #\n");
        textArea.append("#                                                                #\n");
        textArea.append("#      N.B : Seuls sont affichés ici les messages de retour      #\n");
        textArea.append("#            de Ghostlab (debog, messages serveur...)            #\n");
        textArea.append("#                                                                #\n");
        textArea.append("#                          --------------                        #\n");
        textArea.append("#                                                                #\n");
        textArea.append("#            Pour plus d'informations, voir README.md            #\n");
        textArea.append("#         ou tappez la commande help dans votre terminal         #\n");
        textArea.append("#                                                                #\n");
        textArea.append("##################################################################\n");
        textArea.append("\n");
    }

    public static void print(String str) {
        textArea.append(str);
        textArea.setCaretPosition(textArea.getText().length());
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
