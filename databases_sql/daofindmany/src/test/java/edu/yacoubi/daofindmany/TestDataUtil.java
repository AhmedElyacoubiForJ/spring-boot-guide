package edu.yacoubi.daofindmany;

import edu.yacoubi.daofindmany.model.Author;
import edu.yacoubi.daofindmany.model.Book;

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

    public static Book createTestBook() {
        return Book.builder()
                .isbn("978-1-5498-6791-0")
                .title("The Great Gatsby")
                .authorId(1L)
                .build();
    }
}
