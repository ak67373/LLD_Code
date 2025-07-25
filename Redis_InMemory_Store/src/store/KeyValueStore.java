package store;

import exceptions.DataTypeMismatchException;
import models.ValueObject;
import types.AttributeType;
import types.TypeResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static types.TypeResolver.tryParse;

public class KeyValueStore {
    private static final KeyValueStore instance = new KeyValueStore();

    public static KeyValueStore getInstance() {
        return instance;
    }

    private final Map<String, ValueObject> store = new ConcurrentHashMap<>();
    private final Map<String, AttributeType> attributeTypeMap = new ConcurrentHashMap<>();

    private KeyValueStore() {
    }

    public synchronized void put(String key, List<Map.Entry<String, String>> attributes)
            throws DataTypeMismatchException {
        ValueObject obj = new ValueObject();
        for (Map.Entry<String, String> entry : attributes) {
            String attrKey = entry.getKey();
            String rawValue = entry.getValue();
            // to get the exact parsed value like from string like "12" -> 12, "1.0" -> 1.0
            Object parsed = TypeResolver.resolve(attrKey, rawValue, attributeTypeMap);
            obj.putAttribute(attrKey, parsed);
        }
        store.put(key, obj);
    }

    public ValueObject get(String key) {
        return store.get(key);
    }

    public void delete(String key) {
        store.remove(key);
    }

    public List<String> search(String attrKey, String attrValue) {
        List<String> result = new ArrayList<>();

        AttributeType type = attributeTypeMap.get(attrKey);
        if (type == null) return result; // unknown attribute

        Object searchValue = tryParse(attrValue, type); // type-safe parse
        if (searchValue == null) return result; // type mismatch

        for (Map.Entry<String, ValueObject> entry : store.entrySet()) {
            ValueObject obj = entry.getValue();
            Object val = obj.getAttribute(attrKey);
            if (val != null && val.equals(searchValue)) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public Set<String> keys() {
        return store.keySet();
    }
}
