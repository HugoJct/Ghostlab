package main.java.commands.dev;

import java.io.PrintWriter;

import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class CommandHelp extends Command {

    public CommandHelp(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        DebugLogger.print(DebugType.HELP, "###################################################################");
        DebugLogger.print(DebugType.HELP, "#                                                                 #");
        DebugLogger.print(DebugType.HELP, "#                            Commandes                            #");
        DebugLogger.print(DebugType.HELP, "#                                                                 #");
        DebugLogger.print(DebugType.HELP, "#-----------------------------------------------------------------#");
        DebugLogger.print(DebugType.HELP, "#                                                                 #");
        DebugLogger.print(DebugType.HELP, "# DEBUG :                                                         #");
        DebugLogger.print(DebugType.HELP, "#   debug ERROR <0/1> : ON/OFF retour d'erreur                    #");
        DebugLogger.print(DebugType.HELP, "#   debug ALL <0/1> : ON/OFF retour d'erreur                      #");
        DebugLogger.print(DebugType.HELP, "#   debug CONFIRM <0/1> : ON/OFF confirmation lecture commande    #");
        DebugLogger.print(DebugType.HELP, "#   debug LIST <0/1> : affiche liste types debug                  #");
        DebugLogger.print(DebugType.HELP, "# HELP :                                                          #");
        DebugLogger.print(DebugType.HELP, "#   help : afficher liste commandes                               #"); 
        DebugLogger.print(DebugType.HELP, "# KILL :                                                          #");             
        DebugLogger.print(DebugType.HELP, "#   kill : ferme les connections et tue le client                 #");
        DebugLogger.print(DebugType.HELP, "#                                                                 #");
        DebugLogger.print(DebugType.HELP, "###################################################################");
    }
    
}
