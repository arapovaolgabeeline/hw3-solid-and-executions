package org.example.handlers;

import org.example.commands.ICommand;
import org.example.commands.RepeatTwiceAndLogCommand;
import org.example.functions.IHandler;

public class RepeatTwiceAndLogHandler implements IHandler {

    @Override
    public ICommand apply(ICommand command, Exception ex) {
        return new RepeatTwiceAndLogCommand(ex, command);
    }

}
