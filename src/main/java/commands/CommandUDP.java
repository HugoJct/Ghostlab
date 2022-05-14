package main.java.commands;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import main.java.GameInfo;
import main.java.client.ClientUDP;

public abstract class CommandUDP extends Command {

	private DatagramSocket socket;
	private InetAddress addr;

	public CommandUDP(DatagramSocket socket, InetAddress addr) {
		this.socket = socket;
		this.addr = addr;
	}

	public void sendUdpMessage(byte[] message) {
		DatagramPacket packet = new DatagramPacket(message, message.length, addr, GameInfo.portUDP);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract void execute(ClientUDP clientTCP, String[] args);
}