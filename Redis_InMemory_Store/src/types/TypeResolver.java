package types;

import exceptions.DataTypeMismatchException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

public class TypeResolver {
    public static Object resolve(String key, String value, Map<String, AttributeType> typeMap)
            throws DataTypeMismatchException {
        if (!typeMap.containsKey(key)) {
            AttributeType type = detectType(value);
            typeMap.put(key, type);
            return parseValue(type, value);
        } else {
            AttributeType expectedType = typeMap.get(key);
            Object parsed = tryParse(value, expectedType);
            if (parsed == null) throw new DataTypeMismatchException();
            return parsed;
        }
    }

    private static AttributeType detectType(String val) {
        if (val == null || val.trim().isEmpty()) {
            return AttributeType.STRING;
        }

        val = val.trim();

        if (val.equalsIgnoreCase("true") || val.equalsIgnoreCase("false")) {
            return AttributeType.BOOLEAN;
        }

        try {
            Integer.parseInt(val);
            return AttributeType.INTEGER;
        } catch (NumberFormatException ignored) {
        }

        try {
            Double.parseDouble(val);
            return AttributeType.DOUBLE;
        } catch (NumberFormatException ignored) {
        }

        return AttributeType.STRING;
    }

    public static Object tryParse(String value, AttributeType type) {
        if (value == null || value.trim().isEmpty()) return null;

        value = value.trim();

        try {
            switch (type) {
                case BOOLEAN:
                    if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                        return Boolean.parseBoolean(value);
                    }
                    return null;

                case INTEGER:
                    // Accept only valid integers
                    if (value.contains(".") || value.toLowerCase().contains("e")) {
                        return null; // Looks like a double, not int
                    }
                    return Integer.parseInt(value);

                case DOUBLE:
                    // Accept only values that clearly look like double
                    if (!value.contains(".") && !value.toLowerCase().contains("e")) {
                        return null; // It's an integer-looking value, reject for double
                    }
                    return Double.parseDouble(value);

                case STRING:
                    return value;

                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static Object parseValue(AttributeType type, String val) {
        return switch (type) {
            case BOOLEAN -> Boolean.parseBoolean(val);
            case INTEGER -> Integer.parseInt(val);
            case DOUBLE -> Double.parseDouble(val);
            case STRING -> val;
        };
    }
}
