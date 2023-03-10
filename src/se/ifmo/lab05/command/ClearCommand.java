package se.ifmo.lab05.command;

import se.ifmo.lab05.manager.CollectionManager;
import se.ifmo.lab05.util.IOProvider;
import se.ifmo.lab05.exception.InvalidArgsException;

public class ClearCommand extends Command {
    public ClearCommand(IOProvider provider, CollectionManager collection) {
        super("clear", "очистить коллекцию", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        collection.clear();
        provider.getPrinter().print("Collection cleared successfully.\n");
    }
}
