package edu.yacoubi.daofindmany.dao.impl;

import edu.yacoubi.daofindmany.TestDataUtil;
import edu.yacoubi.daofindmany.dao.AuthorDao;
import edu.yacoubi.daofindmany.dao.impl.BookDaoImpl;
import edu.yacoubi.daofindmany.model.Author;
import edu.yacoubi.daofindmany.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTest {

    private AuthorDao authorDao;

    private BookDaoImpl underTest;

    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDao authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        // Given
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);
        Book book = TestDataUtil.createTestBookA();
        book.setAuthorId(author.getId());

        // When
        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());

        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        // Given
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

         // When
        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(author.getId());
        underTest.create(bookA);

        Book bookB = TestDataUtil.createTestBookB();
        bookB.setAuthorId(author.getId());
        underTest.create(bookB);

        Book bookC = TestDataUtil.createTestBookC();
        bookC.setAuthorId(author.getId());
        underTest.create(bookC);

        List<Book> result = underTest.findAll();

        // Then
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }
}
