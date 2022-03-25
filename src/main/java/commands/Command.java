package main.java.commands;

import java.io.PrintWriter;

import main.java.client.Client;

public abstract class Command {

	private PrintWriter out;

	public Command(PrintWriter pw) {
		this.out = pw;
	}

	public void sendMessage(String message) {
		out.println(message);
		out.flush();
	}

	public abstract void execute(Client client, String[] args);
}