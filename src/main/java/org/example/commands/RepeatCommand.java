package org.example.commands;

import java.util.Queue;

// Реализовать Команду, которая повторяет Команду, выбросившую исключение.
public class RepeatCommand implements ICommand {
    private ICommand failedCommand;
    private Queue commandQueue;

    @Override
    public void execute() {
        commandQueue.add(commandQueue);
    }

}
