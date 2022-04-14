package main.java.client;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

import main.java.GameInfo;

public class ClientUDP extends Thread {
    private DatagramSocket socket;
    private InetAddress address;
    
    public ClientUDP(String ip) {
        
        boolean success = false;
        Random r = new Random();
        int port = r.nextInt(9999 - 1000) + 1000;

        while(!success) {
            try {
                socket = new DatagramSocket(port);
                address = InetAddress.getByName(ip);
                success = true;
            } catch (SocketException e) {
                e.printStackTrace();
                port = r.nextInt(9999 - 1000) + 1000;
            }
            catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        GameInfo.portUDP = port;
        
    }

    @Override
    public void run() {

    }

    public DatagramSocket getSocket() {
        return socket;
    }
    public InetAddress getAddr() {
        return address;
    }
}
