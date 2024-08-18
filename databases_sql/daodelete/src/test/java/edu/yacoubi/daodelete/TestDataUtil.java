package edu.yacoubi.daodelete;

import edu.yacoubi.daodelete.model.Author;
import edu.yacoubi.daodelete.model.Book;

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

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("978-1-5498-6791-0")
                .title("The Great Gatsby")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("978-0-307-46330-9")
                .title("To Kill a Mockingbird")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("978-0-316-76958-3")
                .title("1984")
                .authorId(1L)
                .build();
    }
}
