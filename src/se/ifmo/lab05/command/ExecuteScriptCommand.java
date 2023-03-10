package se.ifmo.lab05.command;

import se.ifmo.lab05.manager.CollectionManager;
import se.ifmo.lab05.manager.CommandManager;
import se.ifmo.lab05.util.IOProvider;
import se.ifmo.lab05.exception.InvalidArgsException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ExecuteScriptCommand extends Command {
    private final int recursionDepth;
    public ExecuteScriptCommand(IOProvider provider, CollectionManager collection, int recursionDeth) {
        super("execute_script {file_name}", "считать и исполнить скрипт из указанного файла", provider, collection);
        this.recursionDepth = recursionDeth;
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 1);
        String fileName = args[0];
        try (FileReader fileReader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(fileReader);
            IOProvider provider = new IOProvider(scanner, this.provider.getPrinter());
            CommandManager commandParser = new CommandManager(provider, collection, recursionDepth + 1);
            commandParser.run();
        } catch (FileNotFoundException e) {
            provider.getPrinter().print("File not found or access denied (read).");
        } catch (IOException e) {
            provider.getPrinter().print("Something went wrong while reading.");
        }
    }
}