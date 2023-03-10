package se.ifmo.lab05;

import com.google.gson.JsonParseException;
import se.ifmo.lab05.interfaces.Printer;
import se.ifmo.lab05.managers.CollectionManager;
import se.ifmo.lab05.managers.CommandManager;
import se.ifmo.lab05.parsers.CommandParser;
import se.ifmo.lab05.utils.IOProvider;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Printer printer = new CLIPrinter();
        IOProvider IOProvider = new IOProvider(scanner, printer);
        String fileName = System.getenv("FILENAME");

        if (fileName == null) {
            printer.print("Invalid filename env variable.");
        }
        try {
            CollectionManager collection = CollectionManager.fromFile(fileName);
            printer.print("Collection loaded successfully.");
            CommandManager commandManager = new CommandManager(IOProvider, collection);
            CommandParser commandParser = new CommandParser(commandManager);
            commandParser.run(IOProvider);
        } catch (JsonParseException e) {
            printer.print("Invalid JSON format or invalid input data.");
        } catch (FileNotFoundException e) {
            printer.print("File not found or access denied (read)");
        }
    }
}
