package se.ifmo.lab05.commands;

import se.ifmo.lab05.managers.CollectionManager;
import se.ifmo.lab05.parsers.FlatParser;
import se.ifmo.lab05.utils.IOProvider;
import se.ifmo.lab05.exceptions.InvalidArgsException;
import se.ifmo.lab05.models.Flat;

public class AddCommand extends Command {
    public AddCommand(IOProvider provider, CollectionManager collection) {
        super("add", "добавить новый элемент в коллекцию", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        FlatParser parser = new FlatParser(provider.getScanner(), provider.getPrinter());

        Flat flat = parser.parseFlat();
        // TODO: обработка конца файла или ввода NoSuchElementException
        collection.push(flat);  // TODO: Perhaps ID should be returned from push
        provider.getPrinter().printf("Flat (ID %s) added successfully.\n", flat.getId());

    }
}
