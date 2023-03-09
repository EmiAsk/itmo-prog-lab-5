package se.ifmo.lab05.commands;

import se.ifmo.lab05.managers.CollectionManager;
import se.ifmo.lab05.utils.IOProvider;
import se.ifmo.lab05.exceptions.InvalidArgsException;

public class RemoveByIdCommand extends Command {
    public RemoveByIdCommand(IOProvider provider, CollectionManager collection) {
        super("remove_by_id {id}", "удалить элемент из коллекции по его id", provider, collection);
    }

    @Override
    public void validateArgs(String[] args, int length) throws InvalidArgsException {
        try {
            super.validateArgs(args, length);
            long id = Long.parseLong(args[0]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidArgsException();
        }
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 1);

        long flatId = Long.parseLong(args[0]);
        if (collection.get(flatId) == null) {
            provider.getPrinter().print("Flat with specified ID doesn't exist.");
            return;
        }
        collection.remove(flatId);
        provider.getPrinter().printf("Flat (ID %s) removed successfully.\n", flatId);
    }
}
