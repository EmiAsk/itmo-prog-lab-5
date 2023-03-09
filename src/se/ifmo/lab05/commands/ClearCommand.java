package se.ifmo.lab05.commands;

import se.ifmo.lab05.managers.CollectionManager;
import se.ifmo.lab05.utils.IOProvider;
import se.ifmo.lab05.exceptions.InvalidArgsException;

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
