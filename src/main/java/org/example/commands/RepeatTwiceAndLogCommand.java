package org.example.commands;

/**
 * 9. Реализовать стратегию обработки исключения - повторить два раза, потом записать в лог. Указание: создать новую команду,
 * точно такую же как в пункте 6. Тип этой команды будет показывать, что Команду не удалось выполнить два раза.
 * */
public class RepeatTwiceAndLogCommand implements ICommand {
    private final Exception exception;
    private final ICommand failedCommand;

    public RepeatTwiceAndLogCommand(Exception exception, ICommand failedCommand) {
        this.exception = exception;
        this.failedCommand = failedCommand;
    }

    @Override
    public void execute() {
        try {
            new RepeatCommand(failedCommand).execute();
        } catch (Exception ex) {
            new RepeatLogCommand(exception, failedCommand);
        }
    }

}
