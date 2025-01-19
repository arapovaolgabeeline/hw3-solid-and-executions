package org.example.handlers;

import org.example.commands.ICommand;
import org.example.commands.RepeatLogCommand;
import org.example.functions.IHandler;

/**
 * 8. С помощью Команд из пункта 4 и пункта 6 реализовать следующую обработку исключений:
 * при первом выбросе исключения повторить команду, при повторном выбросе исключения записать информацию в лог.
 * */
public class RepeatLogHandler implements IHandler {

    @Override
    public ICommand apply(ICommand command, Exception ex) {
        return new RepeatLogCommand(ex, command);
    }

}
