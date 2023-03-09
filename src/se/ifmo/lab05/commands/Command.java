package se.ifmo.lab05.commands;

import se.ifmo.lab05.managers.CollectionManager;
import se.ifmo.lab05.utils.IOProvider;
import se.ifmo.lab05.exceptions.InvalidArgsException;

public abstract class Command {
    String name;
    String description;
    IOProvider provider;
    CollectionManager collection;

    public Command(String name, String description, IOProvider provider, CollectionManager collection) {
        this.name = name;
        this.description = description;
        this.provider = provider;
        this.collection = collection;
    }

    public abstract void execute(String[] args) throws Exception;

    public String getDescription() {
        return String.format("%s    |     %s", name, description);
    }

    public void validateArgs(String[] args, int length) throws InvalidArgsException {
        if (args.length != length) {
            throw new InvalidArgsException();
        }
    }
}
