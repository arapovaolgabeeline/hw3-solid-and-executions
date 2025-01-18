package org.example.handlers;

import java.util.LinkedHashMap;
import java.util.Map;
import org.example.commands.ICommand;
import org.example.functions.ICommonFunction;

public class ExceptionHandler {
    private static Map<Class, LinkedHashMap<Class, ICommonFunction>> store;

    public static ICommand handle(ICommand command, Exception ex) {
        Class<? extends ICommand> commandClass = command.getClass();
        Class<? extends Exception> exceptionClass = ex.getClass();

        return store.get(commandClass).get(exceptionClass).apply(command, ex);
    }

    public static void registerHandler(Class commandType, Class exceptionType, ICommonFunction handler) {
        LinkedHashMap<Class, ICommonFunction> exceptionHandlerEntry = store.getOrDefault(commandType, new LinkedHashMap<>());
        exceptionHandlerEntry.put(exceptionType, handler);
        store.put(commandType, exceptionHandlerEntry);
    }

}
