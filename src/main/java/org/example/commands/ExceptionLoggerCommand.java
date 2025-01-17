package org.example.commands;

// Реализовать Команду, которая записывает информацию о выброшенном исключении в лог
public class ExceptionLoggerCommand implements ICommand {
    private final Exception exception;

    public ExceptionLoggerCommand(Exception exception) {
        this.exception = exception;
    }

    @Override
    public void execute() {
        System.out.println(exception);
    }

}
