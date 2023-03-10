package se.ifmo.lab05.manager;

import se.ifmo.lab05.command.*;
import se.ifmo.lab05.exception.ExitException;
import se.ifmo.lab05.exception.InterruptCommandException;
import se.ifmo.lab05.exception.InvalidArgsException;
import se.ifmo.lab05.util.IOProvider;
import se.ifmo.lab05.util.Printer;

import java.util.*;

public class CommandManager {
    private final IOProvider provider;
    private final Map<String, Command> commands = new HashMap<>();
    private final int recursionDepth;
    private final int maxRecursionDepth = getMaxRecDepth();

    private final static int DEFAULT_DEPTH = 5;

    public CommandManager(IOProvider provider, CollectionManager collection, int recursionDepth) {
        this.provider = provider;
        this.recursionDepth = recursionDepth;
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
        register("remove_all_by_furnish", new RemoveByFurnishCommand(provider, collection));
        register("filter_starts_with_name", new FilterNameCommand(provider, collection));
        register("print_unique_house", new PrintUniqueHouseCommand(provider, collection));
        register("execute_script", new ExecuteScriptCommand(provider, collection, recursionDepth));
        register("help", new HelpCommand(provider, collection, commands));


    }

    public static int getMaxRecDepth() {
        try {
            int depth = Integer.parseInt(System.getenv("MAX_RECURSION_DEPTH"));
            return depth > 0 ? depth : DEFAULT_DEPTH;
        } catch (NumberFormatException e) {
            return DEFAULT_DEPTH;
        }
    }

    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public boolean execute(String commandName, String[] args) throws InvalidArgsException {
        if (commands.containsKey(commandName)) {
            commands.get(commandName).execute(args);
            return true;
        }
        return false;
    }

    public void run() {
        Scanner scanner = provider.getScanner();
        Printer printer = provider.getPrinter();

        if (recursionDepth > maxRecursionDepth) {
            return;
        }

        while (true) {
            try {
                printer.printf("Enter command:\n");
                String line = scanner.nextLine();
                String[] splitLine = line.strip().split("\s+");
                String commandName = splitLine[0].toLowerCase();
                String[] args = Arrays.copyOfRange(splitLine, 1, splitLine.length);
                if (!this.execute(commandName, args)) {
                    printer.print("Invalid command");
                }
            } catch (InterruptCommandException e) {
                printer.print("\nExited\n");
            } catch (InvalidArgsException e) {
                printer.print("Invalid arguments. Use command \"help\" to find correct ones.");
            } catch (NoSuchElementException e) {
                printer.print("EOF");
                break;
            } catch (ExitException e) {
                provider.closeScanner();
                provider.getPrinter().print("Program has finished. Good luck!");
                break;
            } catch (Exception e) {
                printer.print("Error occurred: " + e);
            }
        }
    }
}
