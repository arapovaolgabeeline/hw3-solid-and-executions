package org.example.commands;

/**
*  С помощью Команд из пункта 4 и пункта 6 реализовать следующую обработку исключений:
 * при первом выбросе исключения повторить команду, при повторном выбросе исключения записать информацию в лог.
* */
public class RepeatLogCommand implements ICommand {
    private final Exception exception;
    private final ICommand failedCommand;

    public RepeatLogCommand(Exception exception, ICommand failedCommand) {
        this.exception = exception;
        this.failedCommand = failedCommand;
    }

    @Override
    public void execute() {
        try {
            new RepeatCommand(failedCommand).execute();
        } catch (Exception ex) {
            new ExceptionLoggerCommand(ex).execute();
        }
    }

}
