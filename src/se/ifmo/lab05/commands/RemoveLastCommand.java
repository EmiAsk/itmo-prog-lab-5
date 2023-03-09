package se.ifmo.lab05.commands;

import se.ifmo.lab05.managers.CollectionManager;
import se.ifmo.lab05.utils.IOProvider;
import se.ifmo.lab05.exceptions.InvalidArgsException;

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
