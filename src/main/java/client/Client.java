package main.java.client;

public abstract class Client {

    public static void main(String[] args) {
        new ClientTCP(args[0], Integer.parseInt(args[1]));
    }

}