package org.example.commands;

import org.example.EventLoop;

// Реализовать Команду, которая повторяет Команду, выбросившую исключение.
public class RepeatCommand implements ICommand {
    private final ICommand failedCommand;

    public RepeatCommand(ICommand failedCommand) {
        this.failedCommand = failedCommand;
    }

    @Override
    public void execute() {
        // тут нужен IoC, но он будет в следующем дз
        EventLoop.COMMANDS_QUEUE.add(failedCommand);
    }

}
