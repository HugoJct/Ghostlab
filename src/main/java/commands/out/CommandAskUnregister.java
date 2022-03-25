package main.java.commands.out;

import java.io.PrintWriter;

import main.java.commands.Command;

public class CommandAskUnregister extends Command{

    public CommandAskUnregister(String label, PrintWriter pw) {
        super(label, pw);
    }

    @Override
    public void execute(String[] args) {
        
    }
    
}
