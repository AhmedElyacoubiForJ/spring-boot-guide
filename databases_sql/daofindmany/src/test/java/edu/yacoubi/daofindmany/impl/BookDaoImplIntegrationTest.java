package edu.yacoubi.daofindmany.impl;

import edu.yacoubi.daofindmany.TestDataUtil;
import edu.yacoubi.daofindmany.dao.AuthorDao;
import edu.yacoubi.daofindmany.dao.impl.BookDaoImpl;
import edu.yacoubi.daofindmany.model.Author;
import edu.yacoubi.daofindmany.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);
        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());

        // When
        underTest.create(book);
        Optional<Book> result = underTest.find(book.getIsbn());

        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

}
