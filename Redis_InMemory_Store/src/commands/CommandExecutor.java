package commands;
import exceptions.DataTypeMismatchException;
import models.ValueObject;
import store.KeyValueStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CommandExecutor {
    private final KeyValueStore store = KeyValueStore.getInstance();

    public void execute(String input) {
        String[] tokens = input.split(" ");
        String command = tokens[0];

        switch (command) {
            case "put" -> handlePut(tokens);
            case "get" -> handleGet(tokens[1]);
            case "delete" -> store.delete(tokens[1]);
            case "search" -> handleSearch(tokens[1], tokens[2]);
            case "keys" -> handleKeys();
            default -> System.out.println("Invalid command");
        }
    }

    // put sde_bootcamp title SDE-Bootcamp price 30000.00 enrolled false estimated_time 30
    private void handlePut(String[] tokens) {
        String key = tokens[1];
        List<Map.Entry<String, String>> attributes = new ArrayList<>();
        for (int i = 2; i < tokens.length; i += 2) {
            attributes.add(Map.entry(tokens[i], tokens[i + 1]));
        }
        try {
            store.put(key, attributes);
        } catch (DataTypeMismatchException e) {
            System.out.println("Data Type Error");
        }
    }

    private void handleGet(String key) {
        ValueObject value = store.get(key);
        if (value == null) {
            System.out.println("No entry found for " + key);
        } else {
            System.out.println(value);
        }
    }

    private void handleSearch(String attrKey, String attrValue) {
        List<String> results = store.search(attrKey, attrValue);
        Collections.sort(results);
        System.out.println(String.join(",", results));
    }

    private void handleKeys() {
        List<String> keys = new ArrayList<>(store.keys());
        Collections.sort(keys);
        System.out.println(String.join(",", keys));
    }
}
