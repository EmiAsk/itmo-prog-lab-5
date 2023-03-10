package se.ifmo.lab05.command;

import se.ifmo.lab05.exception.InvalidArgsException;
import se.ifmo.lab05.manager.CollectionManager;
import se.ifmo.lab05.model.Flat;
import se.ifmo.lab05.model.House;
import se.ifmo.lab05.util.IOProvider;

import java.util.*;
import java.util.stream.Collectors;

public class PrintUniqueHouseCommand extends Command {
    public PrintUniqueHouseCommand(IOProvider provider, CollectionManager collection) {
        super("print_unique_house",
                "вывести уникальные значения поля house всех элементов в коллекции",
                provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        Set<House> houseSet = collection.getCollection()
                .stream()
                .map(Flat::getHouse)
                .collect(Collectors.toSet());
        String line = "-".repeat(60);
        provider.getPrinter().print(line);
        for (House house : houseSet) {
            provider.getPrinter().print(house.toString());
            provider.getPrinter().print(line);
        }
    }
}
