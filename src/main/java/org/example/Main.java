package org.example;

import org.example.commands.ICommand;
import org.example.commands.MoveCommand;
import org.example.commands.RepeatCommand;
import org.example.handlers.ExceptionHandler;
import org.example.handlers.LoggingExceptionHandler;

public class Main {
    public static void main(String[] args) {
        MoveCommand command = new MoveCommand();

        EventLoop.COMMANDS_QUEUE.add(command);

        ExceptionHandler.registerHandler(MoveCommand.class, Exception.class,
                (ICommand failedCommand, Exception ex) -> new LoggingExceptionHandler());

        EventLoop eventLoop = new EventLoop();


        eventLoop.start();
    }

}