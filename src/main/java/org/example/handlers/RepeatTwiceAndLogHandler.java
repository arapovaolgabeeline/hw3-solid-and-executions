package org.example.handlers;

import org.example.commands.ICommand;
import org.example.commands.RepeatTwiceAndLogCommand;
import org.example.functions.IHandler;

/**
 * Реализовать стратегию обработки исключения - повторить два раза, потом записать в лог. Указание: создать новую команду,
 * точно такую же как в пункте 6. Тип этой команды будет показывать, что Команду не удалось выполнить два раза
 */
public class RepeatTwiceAndLogHandler implements IHandler {

    @Override
    public ICommand apply(ICommand command, Exception ex) {
        return new RepeatTwiceAndLogCommand(ex, command);
    }

}
