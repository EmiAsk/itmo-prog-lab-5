package se.ifmo.lab05.command;

import se.ifmo.lab05.exception.InvalidArgsException;
import se.ifmo.lab05.manager.CollectionManager;
import se.ifmo.lab05.util.IOProvider;

public class ShuffleCommand extends Command {
    public ShuffleCommand(IOProvider IOProvider, CollectionManager collection) {
        super("shuffle", "перемешать элементы коллекции в случайном порядке", IOProvider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        collection.shuffle();
        provider.getPrinter().print("Collection has been shuffled.");
    }
}
