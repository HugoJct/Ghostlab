package main.java.commands.in;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.nio.ByteBuffer;

import main.java.GameInfo;
import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// WELCO m h w f ip port***

public class CommandRcvTcpWelco extends CommandTCP {

    public CommandRcvTcpWelco(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : WELCO");

        if (command.size() < 32) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpWelco/ERROR] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        try {
            GameInfo.registredGameId = command.get(1).byteValue();
        } catch(NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpWelco/ERROR] : les informations données par le serveur pour m ne sont pas conformes, ces informations seront ignorées");
        }

        try {
            // read "h" uint16
            GameInfo.gameHeight = ByteBuffer.wrap(new byte[] { command.get(9).byteValue(), command.get(10).byteValue() }).getInt();
            // read "w" uint16
            GameInfo.gameWidth = ByteBuffer.wrap(new byte[] { command.get(12).byteValue(), command.get(13).byteValue() }).getInt();
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpWelco/ERROR] : les informations données par le serveur pour h et/ou w ne sont pas conformes, ces informations seront ignorées");
        }

        try {
            GameInfo.nbrGhosts = command.get(14).byteValue();
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpWelco/ERROR] : les informations données par le serveur pour f nombre de fantômes ne sont pas conformes, ces informations seront ignorées");
        }

        String ip = "";

        try {
            for (int i = 16 ; i < 31 ; i++) {
                if ((char) command.get(i).byteValue() == '#') {
                    break;
                }
                ip += (char) command.get(i).byteValue();
            }
            GameInfo.ipMulticast = ip;
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpWelco/ERROR] : les informations données par le serveur pour l'ip de multicast ne sont pas conformes, ces informations seront ignorées");
        }

        String port = "";

        try {
            for (int i = 33 ; i < 37 ; i++) {
                port += (char) command.get(i).byteValue();
            }
            GameInfo.portMulticast = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpWelco/ERROR] : les informations données par le serveur pour le port de mutlicast ne sont pas conformes, ces informations seront ignorées");
        }
        
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
