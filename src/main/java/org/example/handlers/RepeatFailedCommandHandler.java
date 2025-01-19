package org.example.handlers;

import org.example.commands.ICommand;
import org.example.commands.RepeatCommand;
import org.example.functions.IHandler;

// Реализовать обработчик исключения, который ставит в очередь Команду - повторитель команды, выбросившей исключение.
public class RepeatFailedCommandHandler implements IHandler {

    @Override
    public ICommand apply(ICommand command, Exception ex) {
        return new RepeatCommand(command);
    }

}
