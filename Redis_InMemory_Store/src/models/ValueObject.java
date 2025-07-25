package models;

import java.util.LinkedHashMap;
import java.util.Map;

public class ValueObject {
    private final Map<String, Object> attributes = new LinkedHashMap<>();

    public void putAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public Map<String, Object> getAllAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        attributes.forEach((k, v) -> {
            sb.append(k).append(": ");
            if (v instanceof Double) {
                sb.append(String.format("%.2f", (Double) v));
            } else {
                sb.append(v);
            }
            sb.append(", ");
        });
        if (sb.length() > 0) sb.setLength(sb.length() - 2);
        return sb.toString();
    }
}
