package edu.yacoubi.daocreateread_integration.impl;

import edu.yacoubi.daocreateread_integration.dao.impl.BookDaoImpl;
import edu.yacoubi.daocreateread_integration.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookDaoImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql() {
        // Given
        Book book = Book.builder()
                .isbn("978-1-5498-6791-0")
                .title("The Great Gatsby")
                .authorId(1L)
                .build();
        // When
        underTest.create(book);

        // Then
        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("978-1-5498-6791-0"),
                eq("The Great Gatsby"),
                eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSql() {
        // When
        underTest.find("978-1-5498-6791-0");

        // Then
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn =? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("978-1-5498-6791-0")
        );
    }
}