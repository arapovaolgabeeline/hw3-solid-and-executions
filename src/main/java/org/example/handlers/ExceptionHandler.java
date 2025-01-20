package org.example.handlers;

import java.util.LinkedHashMap;
import java.util.Map;
import org.example.commands.ICommand;
import org.example.functions.IHandler;

public class ExceptionHandler {
    private static Map<Class, LinkedHashMap<Class, IHandler>> store;

    // 3. Есть множество различных обработчиков исключений. Выбор подходящего обработчика исключения делается на основе
    // экземпляра перехваченного исключения и команды, которая выбросила исключение
    public static ICommand handle(ICommand command, Exception ex) {
        Class<? extends ICommand> commandClass = command.getClass();
        Class<? extends Exception> exceptionClass = ex.getClass();

        return store.get(commandClass).get(exceptionClass).apply(command, ex);
    }

    public static void registerHandler(Class commandType, Class exceptionType, IHandler handler) {
        LinkedHashMap<Class, IHandler> exceptionHandlerEntry = store.getOrDefault(commandType, new LinkedHashMap<>());
        exceptionHandlerEntry.put(exceptionType, handler);
        store.put(commandType, exceptionHandlerEntry);
    }

}
