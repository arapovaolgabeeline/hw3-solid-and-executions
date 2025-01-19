package org.example.handlers;

import org.example.commands.ExceptionLoggerCommand;
import org.example.commands.ICommand;
import org.example.functions.IHandler;

// todo НА САМОМ ДЕЛЕ ХЭНДЛЕР ЭТО НЕ КОМАНДА А МЕТОД - ЛЯМБДА, КОТОРАЯ ПРИНИМАЕТ ДВА ПАРАМЕТРА И ВМЕСТО СЕБЯ СТАВИТ В ОЧЕРЕДЬ КОМАНДУ
// todo И НАМ ТУТ НЕ НУЖЕН ДОСТУП К ОЧЕРЕДИ


// Реализовать обработчик исключения, который ставит Команду, пишущую в лог в очередь Команд
public class LoggingExceptionHandler implements IHandler {

    @Override
    public ICommand apply(ICommand command, Exception ex) {
        return new ExceptionLoggerCommand(ex);
    }

}
