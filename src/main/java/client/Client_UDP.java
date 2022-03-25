package main.java.client;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client_UDP extends Thread {
    private DatagramSocket socket;
    private InetAddress address;
    
    public Client_UDP(String ip) {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName(ip);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void run() {

    }
}
