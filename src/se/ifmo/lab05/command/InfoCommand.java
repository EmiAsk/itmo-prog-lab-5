package se.ifmo.lab05.command;


import se.ifmo.lab05.manager.CollectionManager;
import se.ifmo.lab05.util.IOProvider;
import se.ifmo.lab05.exception.InvalidArgsException;

public class InfoCommand extends Command {
    public InfoCommand(IOProvider provider, CollectionManager collection) {
        super("info", "вывести информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)",
                provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        provider.getPrinter().print(collection.getDescription());
    }


}
