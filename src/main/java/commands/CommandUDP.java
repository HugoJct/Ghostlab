package main.java.commands;

import java.io.PrintWriter;

import main.java.client.ClientUDP;

public abstract class CommandUDP {

	private PrintWriter out;

	public CommandUDP(PrintWriter pw) {
		this.out = pw;
	}

	public void sendMessage(String message) {
		out.println(message);
		out.flush();
	}

	public abstract void execute(ClientUDP clientTCP, String[] args);
}