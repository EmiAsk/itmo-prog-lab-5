package se.ifmo.lab05.commands;

import se.ifmo.lab05.managers.CollectionManager;
import se.ifmo.lab05.utils.IOProvider;
import se.ifmo.lab05.exceptions.InvalidArgsException;

public class ExitCommand extends Command {
    public ExitCommand(IOProvider provider, CollectionManager collection) {
        super("exit", "завершить программу (без сохранения в файл)", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        provider.closeScanner();
        provider.getPrinter().print("Program has finished. Good luck!");
        System.exit(0);
    }
}
