package tr.edu.agu.cs.surplus_match.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Locale;

/**
 * Stored as uppercase enums; accepts common frontend variants when deserialized from JSON.
 */
public enum ProductUnit {
    KG,
    LITER,
    CRATE,
    BOX,
    PORTION,
    UNIT;

    @JsonCreator
    public static ProductUnit fromJson(String raw) {
        if (raw == null || raw.isBlank()) {
            return UNIT;
        }
        String n = raw.trim().toUpperCase(Locale.ROOT);
        return switch (n) {
            case "KGS", "KILOGRAM", "KILOGRAMS" -> KG;
            case "L", "LT", "LITRE", "LITRES", "LITER", "LITERS" -> LITER;
            case "CRATES" -> CRATE;
            case "BOXES" -> BOX;
            case "PORTIONS" -> PORTION;
            case "UNITS" -> UNIT;
            default -> ProductUnit.valueOf(n);
        };
    }
}
