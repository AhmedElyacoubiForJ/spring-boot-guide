package edu.yacoubi.daofindmany.impl;

import edu.yacoubi.daofindmany.TestDataUtil;
import edu.yacoubi.daofindmany.dao.impl.AuthorDaoImpl;
import edu.yacoubi.daofindmany.model.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDaoImplIntegrationTest {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        // Given
        Author author = TestDataUtil.createTestAuthor();

        // When
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());

        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }
}
