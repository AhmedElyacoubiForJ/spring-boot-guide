package edu.yacoubi.database;

import edu.yacoubi.database.model.dto.AuthorDto;
import edu.yacoubi.database.model.dto.BookDto;
import edu.yacoubi.database.model.entity.Author;
import edu.yacoubi.database.model.entity.Book;

public class TestDataUtil {
    private TestDataUtil() {
    }
    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("John Wayne")
                .age(78)
                .build();
    }

    public static AuthorDto createTestAuthorDtoA() {
        return AuthorDto.builder()
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

    public static AuthorDto createTestAuthorDtoB() {
        return AuthorDto.builder()
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

    public static AuthorDto createTestAuthorDtoC() {
        return AuthorDto.builder()
                .id(3L)
                .name("Paul Newman")
                .age(63)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("978-1-5498-6791-0")
                .title("The Great Gatsby")
                .author(author)
                .build();
    }

    public static BookDto createTestBookDtoA(final AuthorDto author) {
        return BookDto.builder()
                .isbn("978-1-5498-6791-0")
                .title("The Great Gatsby")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("978-0-307-46330-9")
                .title("To Kill a Mockingbird")
                .author(author)
                .build();
    }

    public static BookDto createTestBookDtoB(final AuthorDto author) {
        return BookDto.builder()
                .isbn("978-0-307-46330-9")
                .title("To Kill a Mockingbird")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("978-0-316-76958-3")
                .title("1984")
                .author(author)
                .build();
    }

    public static BookDto createTestBookDtoC(final AuthorDto author) {
        return BookDto.builder()
                .isbn("978-0-316-76958-3")
                .title("1984")
                .author(author)
                .build();
    }
}
