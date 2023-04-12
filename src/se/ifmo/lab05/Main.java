package se.ifmo.lab05;

import com.google.gson.JsonParseException;
import se.ifmo.lab05.parser.CommandParser;
import se.ifmo.lab05.util.Printer;
import se.ifmo.lab05.manager.CollectionManager;
import se.ifmo.lab05.manager.CommandManager;
import se.ifmo.lab05.util.CLIPrinter;
import se.ifmo.lab05.util.IOProvider;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    private final static String FILENAME = System.getenv("FILENAME");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Printer printer = new CLIPrinter();
        IOProvider provider = new IOProvider(scanner, printer);

        if (FILENAME == null) {
            printer.print("Invalid filename env variable.");
            return;
        }
        try {
            CollectionManager collection = CollectionManager.fromFile(FILENAME);
            printer.print("Collection loaded successfully.");
            CommandManager commandManager = new CommandManager(collection, provider, 0);
            CommandParser commandParser = new CommandParser(commandManager, provider, 0);
            commandParser.run();
        } catch (JsonParseException e) {
            printer.print("Invalid JSON format or invalid input data.");
        } catch (FileNotFoundException e) {
            printer.print("File not found or access denied (read)");
        }
    }
}
