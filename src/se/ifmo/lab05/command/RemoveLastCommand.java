package se.ifmo.lab05.command;

import se.ifmo.lab05.manager.CollectionManager;
import se.ifmo.lab05.util.IOProvider;
import se.ifmo.lab05.exception.InvalidArgsException;

public class RemoveLastCommand extends Command {
    public RemoveLastCommand(IOProvider provider, CollectionManager collection) {
        super("remove_last", "удалить последний элемент из коллекции", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        collection.pop();
        provider.getPrinter().print("Last collection element removed successfully.");
    }
}
