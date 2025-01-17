package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.example.commands.ICommand;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        boolean stop = false;
        BlockingQueue<ICommand> queue = new ArrayBlockingQueue<>(100);
        ICommand command = null;
        try {
            command = queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (!stop) {
            try {
                command.execute();
            } catch (Exception ex) {
                // execute an exception handler (based on ex type and command)
                ExceptionHandler.handle(command, ex).execute();

                // при первом выбросе исключения повторить команду, при повторном выбросе исключения записать информацию в лог.

                // Реализовать стратегию обработки исключения - повторить два раза, потом записать в лог.
                // Указание: создать новую команду, точно такую же как в пункте 6. Тип этой команды будет показывать, что Команду не удалось выполнить два раза.
            }
        }

    }
}