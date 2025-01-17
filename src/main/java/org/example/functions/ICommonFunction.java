package org.example.functions;

import org.example.commands.ICommand;

@FunctionalInterface
public interface ICommonFunction {

    ICommand apply(ICommand command, Exception ex);

}
