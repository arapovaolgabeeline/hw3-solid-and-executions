package org.example;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import org.example.commands.ExceptionLoggerCommand;
import org.example.commands.ICommand;
import org.example.handlers.LoggingExceptionHandler;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MainTest {

    void test() {
//        ICommand command = null;
//        EventLoop.COMMANDS_QUEUE.add(command);
//
//        ExceptionHandler.registerHandler(ICommand.class, Exception.class, new LoggingExceptionHandler());
//
//        EventLoop eventLoop = new EventLoop();
//        eventLoop.start();
    }

    @Test
    void confirmCommandLogException() throws Exception {
        ExceptionLoggerCommand exceptionLoggerCommand = new ExceptionLoggerCommand(new Exception());

        String exceptionLog = tapSystemOut(exceptionLoggerCommand::execute);

        String expectedExceptionLog = "java.lang.Exception";
        assertEquals(expectedExceptionLog, exceptionLog.trim());
    }

    @Test
    void confirmLoggingExceptionHandlerReturnsExceptionLoggerCommandToQueue() throws Exception {
        LoggingExceptionHandler exceptionLoggerCommand = new LoggingExceptionHandler();

        ICommand command = exceptionLoggerCommand.apply(Mockito.mock(ICommand.class), new Exception());

        assertInstanceOf(ExceptionLoggerCommand.class, command);
    }

}