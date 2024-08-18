package edu.yacoubi.daofindmany.impl;

import edu.yacoubi.daofindmany.TestDataUtil;
import edu.yacoubi.daofindmany.dao.impl.AuthorDaoImpl;
import edu.yacoubi.daofindmany.model.Author;
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
class AuthorDaoImplTest {
    /*
    * This annotation creates a mock object for the JdbcTemplate class.
    * This allows you to test the behavior of the AuthorDaoImpl class
    * without actually interacting with a real JdbcTemplate.
    * */
    @Mock
    private JdbcTemplate jdbcTemplate;

    /*
    * This annotation injects the mock JdbcTemplate into the AuthorDaoImpl instance.
    * This ensures that the AuthorDaoImpl class uses the mock JdbcTemplate during testing.
    * */
    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);

        /*
        * This method verifies that the update method of the JdbcTemplate is called with the expected arguments.
        * This helps ensure that the AuthorDaoImpl class correctly generates the SQL statement for creating an author.
        * */
        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L),
                eq("John Wayne"),
                eq(78)
        );
    }

    @Test
    public void testThatFindOneAuthorGeneratesCorrectSql() {

        underTest.findOne(1L);

        /*
        * This method verifies that the query method of the JdbcTemplate is called with the expected arguments.
        * This helps ensure that the AuthorDaoImpl class correctly
        * generates the SQL statement for finding an author by ID.
        * */
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id =? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindAllAuthorsGeneratesCorrectSql() {
        underTest.findAll();

        /*
        * This method verifies that the query method of the JdbcTemplate
        * is called with the expected arguments.
        * This helps ensure that the AuthorDaoImpl class correctly
        * generates the SQL statement for finding all authors.
        * */
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }
}