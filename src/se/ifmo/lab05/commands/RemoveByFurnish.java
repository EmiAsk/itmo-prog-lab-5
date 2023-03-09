package se.ifmo.lab05.commands;

import se.ifmo.lab05.exceptions.InvalidArgsException;
import se.ifmo.lab05.managers.CollectionManager;
import se.ifmo.lab05.models.enums.Furnish;
import se.ifmo.lab05.utils.IOProvider;

public class RemoveByFurnish extends Command {
    public RemoveByFurnish(IOProvider provider, CollectionManager collection) {
        super("remove_all_by_furnish {furnish}",
                "удалить из коллекции все элементы, значение поля furnish которого эквивалентно заданному",
                provider, collection);
    }

    @Override
    public void validateArgs(String[] args, int length) throws InvalidArgsException {
        super.validateArgs(args, length);
        try {
            Furnish furnish = Furnish.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            throw new InvalidArgsException();
        }
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 1);
        Furnish furnish = Furnish.valueOf(args[0]);
        long n = collection.removeByFurnish(furnish);
        provider.getPrinter().printf("%s flats with Furnish [%s] removed successfully.\n", n, furnish);
    }
}
