package org.example;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import org.example.commands.ExceptionLoggerCommand;
import org.example.commands.ICommand;
import org.example.commands.RepeatCommand;
import org.example.commands.RepeatLogCommand;
import org.example.commands.RepeatTwiceAndLogCommand;
import org.example.handlers.LoggingExceptionHandler;
import org.example.handlers.RepeatFailedCommandHandler;
import org.example.handlers.RepeatOnceThenLogHandler;
import org.example.handlers.RepeatTwiceThenLogHandler;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;

class MainTest {

    @Test
    void confirmCommandLogsException() throws Exception {
        // given
        ExceptionLoggerCommand exceptionLoggerCommand = new ExceptionLoggerCommand(new Exception());

        // when
        String exceptionLog = tapSystemOut(exceptionLoggerCommand::execute);

        // then
        String expectedExceptionLog = "java.lang.Exception";
        assertEquals(expectedExceptionLog, exceptionLog.trim());
    }

    @Test
    void confirmLoggingExceptionHandlerReturnsExceptionLoggerCommand() {
        // given
        LoggingExceptionHandler loggingExceptionHandler = new LoggingExceptionHandler();

        // when
        ICommand command = loggingExceptionHandler.apply(Mockito.mock(ICommand.class), new Exception());

        // then
        assertInstanceOf(ExceptionLoggerCommand.class, command);
    }

    @Test
    void confirmCommandRepeatsFailedCommand() {
        // given
        ICommand failedCommand = Mockito.mock(ICommand.class);
        RepeatCommand repeatCommand = new RepeatCommand(failedCommand);

        // when
        repeatCommand.execute();

        // then
        assertNumberOfRepeats(1, failedCommand);
    }

    @Test
    void confirmRepeatFailedCommandHandlerReturnsRepeatCommand() {
        // given
        ICommand failedCommand = Mockito.mock(ICommand.class);
        RepeatFailedCommandHandler repeatFailedCommandHandler = new RepeatFailedCommandHandler();

        // when
        ICommand command = repeatFailedCommandHandler.apply(failedCommand, new Exception());

        // then
        assertInstanceOf(RepeatCommand.class, command);
    }

    // ___________________________________________________

    @Test
    void shouldRepeatCommandOnceThenLogException() throws Exception {
        // given
        ICommand failedCommand = createCommandAlwaysReturnsException();
        RepeatOnceThenLogHandler repeatOnceThenLogHandler = new RepeatOnceThenLogHandler();

        // when
        ICommand command = repeatOnceThenLogHandler.apply(failedCommand, new Exception());

        // then
        assertInstanceOf(RepeatLogCommand.class, command);
        assertExceptionLogWasReceived(command);
        assertNumberOfRepeats(1, failedCommand);
    }

    // ___________________________________________________
    @Test
    void shouldRepeatCommandTwiceThenLogException() throws Exception {
        // given
        ICommand failedCommand = createCommandAlwaysReturnsException();
        RepeatTwiceThenLogHandler repeatTwiceThenLogHandler = new RepeatTwiceThenLogHandler();

        // when
        ICommand command = repeatTwiceThenLogHandler.apply(failedCommand, new Exception());
        command.execute();

        // then
        assertInstanceOf(RepeatTwiceAndLogCommand.class, command);
        assertNumberOfRepeats(2, failedCommand);
        assertExceptionLogWasReceived(command);
    }

    private static ICommand createCommandAlwaysReturnsException() {
        ICommand failedCommand = Mockito.mock(ICommand.class);
        doThrow(new RuntimeException()).when(failedCommand).execute();
        return failedCommand;
    }

    private static void assertExceptionLogWasReceived(ICommand command) throws Exception {
        String exceptionLog = tapSystemOut(command::execute);
        String expectedExceptionLog = "java.lang.Exception";
        assertEquals(expectedExceptionLog, exceptionLog.trim());
    }

    private static void assertNumberOfRepeats(int expectedTimes, ICommand command) {
        Mockito.verify(command, Mockito.times(expectedTimes)).execute();
    }

}