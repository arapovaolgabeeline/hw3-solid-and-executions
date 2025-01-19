package org.example;

import org.example.commands.ICommand;
import org.example.handlers.ExceptionHandler;
import org.example.handlers.LoggingExceptionHandler;

public class Main {
    public static void main(String[] args) {
        ICommand command = null;
        EventLoop.COMMANDS_QUEUE.add(command);

        ExceptionHandler.registerHandler(ICommand.class, Exception.class, new LoggingExceptionHandler());

        EventLoop eventLoop = new EventLoop();
        eventLoop.start();
    }

}