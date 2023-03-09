package se.ifmo.lab05.commands;

import se.ifmo.lab05.managers.CollectionManager;
import se.ifmo.lab05.utils.IOProvider;
import se.ifmo.lab05.exceptions.InvalidArgsException;

import java.util.Map;

public class HelpCommand extends Command {
    private final Map<String, Command> commands;

    public HelpCommand(IOProvider provider, CollectionManager collection, Map<String, Command> commands) {
        super("help", "вывести справку по доступным командам", provider, collection);
        this.commands = commands;
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        String line = "-".repeat(150);
        provider.getPrinter().print(line);
        for (Command command : commands.values()) {
            provider.getPrinter().print(command.getDescription());
            provider.getPrinter().print(line);
        }
    }
}
