package org.example.functions;

import org.example.commands.ICommand;

@FunctionalInterface
public interface IHandler {

    ICommand apply(ICommand command, Exception ex);

}
