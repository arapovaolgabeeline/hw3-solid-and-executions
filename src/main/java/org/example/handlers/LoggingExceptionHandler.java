package org.example.handlers;

import org.example.EventLoop;
import org.example.commands.ICommand;
import org.example.functions.ICommonFunction;

// Реализовать обработчик исключения, который ставит Команду, пишущую в лог в очередь Команд
// todo НА САМОМ ДЕЛЕ ХЭНДЛЕР ЭТО НЕ КОМАНДА А МЕТОД - ЛЯМБДА, КОТОРАЯ ПРИНИМАЕТ ДВА ПАРАМЕТРА И ОТДАЕТ КОМАНДУ
// todo И НАМ ТУТ НЕ НУЖЕН ДОСТУП К ОЧЕРЕДИ
public class LoggingExceptionHandler implements ICommonFunction {
    private final ICommand failedCommand;

    public LoggingExceptionHandler(ICommand failedCommand) {
        this.failedCommand = failedCommand;
    }

    @Override
    public ICommand apply(ICommand command, Exception ex) {
        return failedCommand;
    }
}
