package se.ifmo.lab05.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import se.ifmo.lab05.models.Flat;
import se.ifmo.lab05.models.enums.Furnish;
import se.ifmo.lab05.utils.ZonedDateTimeDeserializer;
import se.ifmo.lab05.utils.ZonedDateTimeSerializer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.ZonedDateTime;
import java.util.*;

public class CollectionManager {
    private final Stack<Flat> collection;
    private final ZonedDateTime createdAt = ZonedDateTime.now();

    public CollectionManager() {
        collection = new Stack<>();
    }

    public List<Flat> getCollection() {
        return collection;
    }

    private static Flat[] parseFile(FileReader file) throws JsonParseException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        Gson gson = builder.create();
        StringBuilder inputData = new StringBuilder();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            inputData.append(scanner.nextLine());
        }
        return gson.fromJson(inputData.toString(), Flat[].class);
    }

    public static CollectionManager fromFile(String fileName) throws FileNotFoundException {
        CollectionManager collection = new CollectionManager();
        Flat[] flats = parseFile(new FileReader(fileName));
        for (Flat flat : flats) {
            if (flat.validate()) collection.push(flat);
        }
        return collection;
    }

    public void push(Flat element) {
        collection.push(element);
    }

    public void update(long id, Flat newFlat) {
        Flat flat = get(id);
        flat.update(newFlat);
    }

    public void remove(long id) {
        collection.removeElement(get(id));
    }

    public void clear() {
        collection.clear();
    }

    public void pop() {
        if (!collection.isEmpty()) collection.pop();
    }

    public Flat get(long id) {
        for (Flat flat : collection) if (flat.getId() == id) return flat;
        return null;
    }

    public String getDescription() {
        return String.format("Тип: %s\nДата инициализации: %s\nКол-во элементов: %s",
                CollectionManager.class.getName(), createdAt, collection.size());
    }

    public String dump() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer());
        Gson gson = builder.create();
        // TODO: Consider cloning collection before dump
        return gson.toJson(collection.toArray(), Flat[].class);
    }

    public boolean validate() {
        HashSet<Long> idSet = new HashSet<>();
        for (Flat flat : collection) {
            if (!flat.validate()) {
                return false;
            }
            idSet.add(flat.getId());
        }
        return (idSet.size() == collection.size());
    }

    public Flat min() {
        return !collection.isEmpty() ? collection.stream()
                .min(Flat::compareTo)
                .get() : null;
    }

    public void shuffle() {
        Collections.shuffle(collection);
    }

    public long removeByFurnish(Furnish furnish) {
        long n = collection
                .stream()
                .filter(flat -> flat.getFurnish() == furnish)
                .count();
        collection.removeIf(flat -> flat.getFurnish() == furnish);
        return n;
    }

    public List<Flat> filterByName(String name) {
        return collection
                .stream()
                .filter(flat -> flat.getName().toLowerCase().startsWith(name.toLowerCase()))
                .toList();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Flat flat : collection) {
            builder.append(flat);
            builder.append("\n");
        }
        return builder.toString();
    }
}
