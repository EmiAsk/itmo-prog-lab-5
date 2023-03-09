package se.ifmo.lab05.managers;

import se.ifmo.lab05.utils.IOProvider;
import se.ifmo.lab05.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandManager(IOProvider provider, CollectionManager collection) {
        register("info", new InfoCommand(provider, collection));
        register("show", new ShowCommand(provider, collection));
        register("add", new AddCommand(provider, collection));
        register("update", new UpdateCommand(provider, collection));
        register("remove_by_id", new RemoveByIdCommand(provider, collection));
        register("clear", new ClearCommand(provider, collection));
        register("save", new SaveCommand(provider, collection));
        register("exit", new ExitCommand(provider, collection));
        register("remove_last", new RemoveLastCommand(provider, collection));
        register("add_if_min", new AddIfMinCommand(provider, collection));
        register("shuffle", new ShuffleCommand(provider, collection));
        register("remove_all_by_furnish", new RemoveByFurnish(provider, collection));
        register("filter_starts_with_name", new FilterNameCommand(provider, collection));
        register("print_unique_house", new PrintUniqueHouse(provider, collection));
        register("execute_script", new ExecuteScriptCommand(provider, collection));
        register("help", new HelpCommand(provider, collection, commands));
    }

    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public boolean execute(String commandName, String[] args) throws Exception {
        if (commands.containsKey(commandName)) {
            commands.get(commandName).execute(args);
            return true;
        }
        return false;
    }

}
