package me.picknchew.teachassistapi.model;

import java.util.HashMap;
import java.util.Map;

public enum Category {
    KNOWLEDGE("K"),
    THINKING("T"),
    COMMUNICATION("C"),
    APPLICATION("A"),
    OTHER("O", "__empty__");

    private static final Map<String, Category> identifierLookup = new HashMap<String, Category>();

    static {
        for (Category category : Category.values()) {
            for (String identifier : category.identifiers) {
                identifierLookup.put(identifier, category);
            }
        }
    }

    private final String[] identifiers;

    Category(String... identifiers) {
        this.identifiers = identifiers;
    }

    public static Category get(String identifier) {
        return identifierLookup.get(identifier);
    }
}
