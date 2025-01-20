package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.example.commands.ICommand;
import org.example.handlers.ExceptionHandler;

public class EventLoop {
    public static BlockingQueue<ICommand> COMMANDS_QUEUE = new ArrayBlockingQueue<>(100);

    // InterruptedException вынесен в сигнатуру, поскольку забор команды из очереди не относится к игровым операциям,
    // требующим перехвата ExceptionHandler-ми
    public void start() throws InterruptedException {
        boolean stop = false;

        while (!stop) {
            ICommand command = COMMANDS_QUEUE.take();

            // 1. Обернуть вызов Команды в блок try-catch.
            try {
                command.execute();
            } catch (Exception ex) { // 2. Обработчик catch должен перехватывать только самое базовое исключение.
                ExceptionHandler.handle(command, ex).execute();
            }
        }

    }

}
