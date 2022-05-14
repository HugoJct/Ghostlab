package main.java.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Random;

import main.java.GameInfo;
import main.java.commands.CommandUDP;
import main.java.commands.in.CommandRcvUdpEndGame;
import main.java.commands.in.CommandRcvUdpGhostPos;
import main.java.commands.in.CommandRcvUdpMessage;
import main.java.commands.in.CommandRcvUdpScore;
import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class ClientUDP extends Thread {
    private DatagramSocket socket;
    private MulticastSocket multSocket;
    private InetAddress address;

    private HashMap<String,CommandUDP> commandRcvUdpList = new HashMap<String,CommandUDP>();
    
    public static boolean clientUDPCreated = false;

    public ClientUDP(String ip) {
        
        boolean success = false;
        Random r = new Random();
        int port = r.nextInt(9999 - 1000) + 1000;

        int attempt = 1;

        DebugLogger.print(DebugType.COM, "Création du client UDP...");

        while(!success) {
            try {
                
                DebugLogger.print(DebugType.COM, "Tentative numéro " + attempt + "...");
                
                socket = new DatagramSocket(port);
                address = InetAddress.getByName(ip);
                
                DebugLogger.print(DebugType.COM, "...succès");
                GameInfo.portUDP = port;
                
                // remplissage de la liste de commandes recevables
                commandRcvUdpList.put("GHOST", new CommandRcvUdpGhostPos(socket, address));
                commandRcvUdpList.put("ENDGA", new CommandRcvUdpEndGame(socket, address));
                commandRcvUdpList.put("MESSA", new CommandRcvUdpMessage(socket, address));
                commandRcvUdpList.put("SCORE", new CommandRcvUdpScore(socket, address));
                success = true;

            } catch (SocketException e) {
                DebugLogger.print(DebugType.ERROR, "[ClientUDP/ERREUR] : port UDP déjà utilisé");
                attempt++;
                port = r.nextInt(9999 - 1000) + 1000;
            }
            catch (UnknownHostException e) {
                DebugLogger.print(DebugType.ERROR, "[ClientUDP/ERREUR] : : l'adresse IP de l'hôte ne peut être déterminée");
                break;
            }
        }

        clientUDPCreated = true;
    }

    @Override
    public void run() {

        while(Client.isConnected) {
            try {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                
                String message = new String(packet.getData(), 0, packet.getLength());
                
                String[] args = message.split(" ");

                // suppression des caractères de fin de ligne "+++"
                args[args.length-1].substring(args[args.length-1].length()-4, args[args.length-1].length()-1);
                
                if(commandRcvUdpList.containsKey(args[0])) {
                    commandRcvUdpList.get(args[0]).execute(this, args);
                }
                else {
                    DebugLogger.print(DebugType.CONFIRM, "Commande inconnue : " + args[0]);
                }
            } catch (IOException e) {
                DebugLogger.print(DebugType.ERROR, "[ClientTCP/ERREUR] : erreur lors de la réception d'un message UDP");
                Console.useMessage("killclient");
            }
        }
        clientUDPCreated = false;
        socket.close();
        multSocket.close();
    }

    public void joinMulticast() {
        try {
            multSocket = new MulticastSocket(GameInfo.portMulticast);
            multSocket.joinGroup(InetAddress.getByName(GameInfo.ipMulticast));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public InetAddress getAddr() {
        return address;
    }
}
