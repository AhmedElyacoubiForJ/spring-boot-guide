package edu.yacoubi.daofindmany;

import edu.yacoubi.daofindmany.model.Author;
import edu.yacoubi.daofindmany.model.Book;

public final class TestDataUtil {
    private TestDataUtil() {
    }
    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("John Wayne")
                .age(78)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Jesse James")
                .age(49)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Paul Newman")
                .age(63)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("978-1-5498-6791-0")
                .title("The Great Gatsby")
                .authorId(1L)
                .build();
    }
}
