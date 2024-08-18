package edu.yacoubi.database.repositories;

import edu.yacoubi.database.TestDataUtil;
import edu.yacoubi.database.model.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        // Given
        Author author = TestDataUtil.createTestAuthorA();

        // When
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());

        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRetrievedAll() {
        // Given
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);
        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.save(authorC);

        // When
        Iterable<Author> result = underTest.findAll();

        // Then
        assertThat(result)
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        // Given
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);

        // When
        author.setName("Updated Author");
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());

        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        // Given
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);

        // When
        underTest.delete(author);
        Optional<Author> result = underTest.findById(author.getId());

        // Then
        assertThat(result).isEmpty();
    }
}
