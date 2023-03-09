package se.ifmo.lab05.parsers;

import se.ifmo.lab05.exceptions.ExitException;
import se.ifmo.lab05.interfaces.Parser;
import se.ifmo.lab05.interfaces.Printer;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class DefaultArgParser implements Parser {
    private final Scanner input;
    private final Printer printer;

    public DefaultArgParser(Scanner scanner, Printer printer) {
        this.input = scanner;
        this.printer = printer;
    }

    public String parseString(String name, String descr) throws NoSuchElementException {
        if (descr == null || descr.equals("")) {
            printer.printf("Enter %s: ", name);
        } else {
            printer.printf("Enter %s (%s): ", name, descr);
        }
        String line = input.nextLine().strip();

        if (line.equals("exit")) {
            throw new ExitException();
        }
        return line.equals("") ? null : line;
    }

    public Integer parseInt(String name, String descr) {
        while (true) {
            try {
                String line = parseString(name, descr);
                return (line != null) ? Integer.parseInt(line) : null;
            } catch (NumberFormatException exception) {
                printer.printf("Invalid %s.%n", name);
            }
        }
    }

    public Long parseLong(String name, String descr) {
        while (true) {
            try {
                String line = parseString(name, descr);
                return (line != null) ? Long.parseLong(line) : null;
            } catch (NumberFormatException exception) {
                printer.printf("Invalid %s.%n", name);
            }
        }
    }

    public Float parseFloat(String name, String descr) {
        while (true) {
            try {
                String line = parseString(name, descr);
                return (line != null) ? Float.parseFloat(line) : null;
            } catch (NumberFormatException exception) {
                printer.printf("Invalid %s.%n", name);
            }
        }
    }

    public Double parseDouble(String name, String descr) {
        while (true) {
            try {
                String line = parseString(name, descr);
                return (line != null) ? Double.parseDouble(line) : null;
            } catch (NumberFormatException exception) {
                printer.printf("Invalid %s.%n", name);
            }
        }
    }

    public <T extends Enum<T>> T parseEnum(Class<T> enumType, String name, String descr) {
        while (true) {
            try {
                String line = parseString(name, descr);
                return (line != null) ? Enum.valueOf(enumType, line) : null;
            } catch (NullPointerException | IllegalArgumentException exception) {
                printer.printf("Invalid %s.%n", name);
            }
        }
    }

    public void print(String text) {
        printer.print(text);
    }

}
