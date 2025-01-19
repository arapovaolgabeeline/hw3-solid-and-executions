package org.example.commands;

// Реализовать Команду, которая повторяет Команду, выбросившую исключение.
public class RepeatCommand implements ICommand {
    private final ICommand failedCommand;

    public RepeatCommand(ICommand failedCommand) {
        this.failedCommand = failedCommand;
    }

    @Override
    public void execute() {
        failedCommand.execute();
    }

}
