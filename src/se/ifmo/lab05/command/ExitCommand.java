package se.ifmo.lab05.command;

import se.ifmo.lab05.exception.ExitException;
import se.ifmo.lab05.manager.CollectionManager;
import se.ifmo.lab05.util.IOProvider;
import se.ifmo.lab05.exception.InvalidArgsException;

public class ExitCommand extends Command {
    public ExitCommand(IOProvider provider, CollectionManager collection) {
        super("exit", "завершить программу (без сохранения в файл)", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        throw new ExitException();
    }
}
