package main.java.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;

import main.java.GameInfo;
import main.java.commands.CommandUDP;
import main.java.commands.in.CommandRcvMultUdpEndGame;
import main.java.commands.in.CommandRcvMultUdpGhostPos;
import main.java.commands.in.CommandRcvMultUdpMessage;
import main.java.commands.in.CommandRcvMultUdpScore;
import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class Multicast extends Thread {
    private MulticastSocket multSocket;

    private HashMap<String,CommandUDP> commandRcvMulticastUdpList = new HashMap<String,CommandUDP>();
    
    public Multicast() {
        try {
            multSocket = new MulticastSocket(GameInfo.portMulticast);
            multSocket.joinGroup(InetAddress.getByName(GameInfo.ipMulticast));

            // remplissage de la liste de commandes recevables
            commandRcvMulticastUdpList.put("GHOST", new CommandRcvMultUdpGhostPos());
            commandRcvMulticastUdpList.put("ENDGA", new CommandRcvMultUdpEndGame());
            commandRcvMulticastUdpList.put("MESSA", new CommandRcvMultUdpMessage());
            commandRcvMulticastUdpList.put("SCORE", new CommandRcvMultUdpScore());
        } catch (IOException e) {
            DebugLogger.print(DebugType.ERROR, "[ClientUDP/ERREUR] : erreur lors de la connexion au multicast");
            Console.useMessage("killclient");
        }
    }

    @Override
    public void run() {

        while (Client.isConnected) {
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            
            try {
                multSocket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                
                String[] args = message.split(" ");
                
                if(commandRcvMulticastUdpList.containsKey(args[0])) {
                    commandRcvMulticastUdpList.get(args[0]).execute(args);
                }

            } catch (IOException e) {
                DebugLogger.print(DebugType.ERROR, "[Multicast/ERREUR] : erreur lors de la réception d'un packet multicast");
                Console.useMessage("killclient");
            }
        }
        multSocket.close();
    }
}
