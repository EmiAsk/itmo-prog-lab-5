package se.ifmo.lab05.commands;

import se.ifmo.lab05.exceptions.InvalidArgsException;
import se.ifmo.lab05.managers.CollectionManager;
import se.ifmo.lab05.models.Flat;
import se.ifmo.lab05.models.House;
import se.ifmo.lab05.utils.IOProvider;

import java.util.*;
import java.util.stream.Collectors;

public class PrintUniqueHouse extends Command {
    public PrintUniqueHouse(IOProvider provider, CollectionManager collection) {
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
