package edu.yacoubi.daodelete.dao.impl;

import edu.yacoubi.daodelete.TestDataUtil;
import edu.yacoubi.daodelete.dao.impl.BookDaoImpl;
import edu.yacoubi.daodelete.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/*
 * This annotation is used to integrate Mockito with JUnit Jupiter,
 * allowing for the use of Mockito's annotations like @Mock and @InjectMocks.
 * */
@ExtendWith(MockitoExtension.class)
class BookDaoImplTest {
    /*
     * This annotation creates a mock object for the JdbcTemplate class.
     * This allows you to test the behavior of the BookDaoImpl class
     * without actually interacting with a real JdbcTemplate.
     * */
    @Mock
    private JdbcTemplate jdbcTemplate;

    /*
     * This annotation injects the mock JdbcTemplate into the BookDaoImpl instance.
     * This ensures that the BookDaoImpl class uses the mock JdbcTemplate during testing.
     * */
    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql() {
        // Given
        Book book = TestDataUtil.createTestBookA();
        // When
        underTest.create(book);

        // Then
        /*
         * This method verifies that the update method of the JdbcTemplate is called with the expected arguments.
         * This helps ensure that the BookDaoImpl class correctly generates the SQL statement for creating a book.
         * */
        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("978-1-5498-6791-0"), eq("The Great Gatsby"), eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSql() {
        // When
        underTest.findOne("978-1-5498-6791-0");

        // Then
        /*
         * This method verifies that the query method of the JdbcTemplate is called with the expected arguments.
         * This helps ensure that the BookDaoImpl class correctly
         * generates the SQL statement for finding a book by ISBN.
         * */
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn =? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(), eq("978-1-5498-6791-0")
        );
    }

    @Test
    public void testThatFindAllBooksGeneratesCorrectSql() {
        // When
        underTest.findAll();

        // Then
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateBookGeneratesCorrectSql() {
        // Given
        Book book = TestDataUtil.createTestBookA();
        // When
        underTest.update("978-1-5498-6791-0", book);

        // Then
        /*
         * This method verifies that the update method of the JdbcTemplate is called with the expected arguments.
         * This helps ensure that the BookDaoImpl class correctly generates the SQL statement for updating a book.
         * */
        verify(jdbcTemplate).update(
                eq("UPDATE books SET isbn=?, title =?, author_id =? WHERE isbn =?"),
                eq("978-1-5498-6791-0"), eq("The Great Gatsby"), eq(1L), eq("978-1-5498-6791-0")
        );
    }

    @Test
    public void testThatDeleteBookGeneratesCorrectSql() {
        // Given
        // When
        underTest.delete("978-1-5498-6791-0");

        // Then
        /*
         * This method verifies that the update method
         * of the JdbcTemplate is called with the expected arguments.
         * This helps ensure that the BookDaoImpl class
         * correctly generates the SQL statement for deleting a book.
         * */
        verify(jdbcTemplate).update(
                eq("DELETE FROM books WHERE isbn =?"),
                eq("978-1-5498-6791-0")
        );
    }
}