package org.example.commands;

public class MoveCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("Moved");
    }

}
