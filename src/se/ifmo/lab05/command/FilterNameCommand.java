package se.ifmo.lab05.command;

import se.ifmo.lab05.exception.InvalidArgsException;
import se.ifmo.lab05.manager.CollectionManager;
import se.ifmo.lab05.model.Flat;
import se.ifmo.lab05.util.IOProvider;

import java.util.List;

public class FilterNameCommand extends Command {
    public FilterNameCommand(IOProvider provider, CollectionManager collection) {
        super("filter_starts_with_name {name}",
                "вывести элементы, значение поля name которых начинается с заданной подстроки",
                provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 1);
        String name = args[0];
        List<Flat> filteredCollection = collection.filterByName(name);
        provider.getPrinter().print("Collection filtered by name:");
        String line = "-".repeat(60);
        provider.getPrinter().print(line);
        for (Flat flat : filteredCollection) {
            provider.getPrinter().print(flat.toString());
            provider.getPrinter().print(line);
        }
    }
}
