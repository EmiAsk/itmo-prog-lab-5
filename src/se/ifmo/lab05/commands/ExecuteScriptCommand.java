package se.ifmo.lab05.commands;

import se.ifmo.lab05.managers.CollectionManager;
import se.ifmo.lab05.managers.CommandManager;
import se.ifmo.lab05.parsers.CommandParser;
import se.ifmo.lab05.utils.IOProvider;
import se.ifmo.lab05.exceptions.InvalidArgsException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ExecuteScriptCommand extends Command {
    public ExecuteScriptCommand(IOProvider provider, CollectionManager collection) {
        super("execute_script {file_name}", "считать и исполнить скрипт из указанного файла", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 1);
        String fileName = args[0];
        try (FileReader fileReader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(fileReader);
            IOProvider provider = new IOProvider(scanner, this.provider.getPrinter());
            CommandManager commandManager = new CommandManager(provider, collection);
            CommandParser commandParser = new CommandParser(commandManager);
            commandParser.run(provider);
        } catch (FileNotFoundException e) {
            provider.getPrinter().print("File not found or access denied (read).");
        } catch (IOException e) {
            provider.getPrinter().print("Something went wrong while reading.");
        }
    }
}