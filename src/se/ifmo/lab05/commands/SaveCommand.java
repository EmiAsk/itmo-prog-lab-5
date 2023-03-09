package se.ifmo.lab05.commands;

import se.ifmo.lab05.managers.CollectionManager;
import se.ifmo.lab05.utils.IOProvider;
import se.ifmo.lab05.exceptions.InvalidArgsException;

import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends Command {
    public SaveCommand(IOProvider provider, CollectionManager collection) {
        super("save", "сохранить коллекцию в файл", provider, collection);
    }

    @Override
    public void execute(String[] args) throws IOException, InvalidArgsException {
        validateArgs(args, 0);
        FileWriter fileWriter = new FileWriter(System.getenv("FILENAME"));
        fileWriter.write(collection.dump());
        fileWriter.close();
        provider.getPrinter().printf("Collection dumped to file (%s) successfully.\n", System.getenv("FILENAME"));
    }
}
