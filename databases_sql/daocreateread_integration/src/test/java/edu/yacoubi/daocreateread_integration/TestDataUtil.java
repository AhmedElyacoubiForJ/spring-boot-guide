package edu.yacoubi.daocreateread_integration;

import edu.yacoubi.daocreateread_integration.model.Author;

public final class TestDataUtil {
    private TestDataUtil() {
    }
    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Thomas")
                .age(23)
                .build();
    }
}
