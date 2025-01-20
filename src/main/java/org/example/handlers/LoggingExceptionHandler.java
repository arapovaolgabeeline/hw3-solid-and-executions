package org.example.handlers;

import org.example.commands.ExceptionLoggerCommand;
import org.example.commands.ICommand;
import org.example.functions.IHandler;

// 5. Реализовать обработчик исключения, который ставит Команду, пишущую в лог в очередь Команд
public class LoggingExceptionHandler implements IHandler {

    @Override
    public ICommand apply(ICommand command, Exception ex) {
        return new ExceptionLoggerCommand(ex);
    }

}
