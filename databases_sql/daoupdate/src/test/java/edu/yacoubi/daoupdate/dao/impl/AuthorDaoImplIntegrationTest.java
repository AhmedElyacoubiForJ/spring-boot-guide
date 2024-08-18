package edu.yacoubi.daoupdate.dao.impl;

import edu.yacoubi.daoupdate.TestDataUtil;
import edu.yacoubi.daoupdate.model.Author;
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
/*
* The `@DirtiesContext` annotation is used in JUnit 5 to mark a test class or test method as dirty,
* meaning that the Spring application context will be recreated after the test execution.
* In this case, the `AuthorDaoImplIntegrationTest` class is marked as dirty,
* and the Spring application context will be recreated after each test method.
* This ensures that any changes made to the database during a test method are rolled back,
* allowing for isolation between test cases.
* */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTest {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        // Given
        Author author = TestDataUtil.createTestAuthorA();

        // When
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());

        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRetrievedAll() {
        // Given
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.create(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.create(authorB);
        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.create(authorC);

        // When
        List<Author> result = underTest.findAll();

        // Then
        assertThat(result)
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        // Given
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);

        // When
        author.setName("Updated Author");
        underTest.update(author.getId(), author);
        Optional<Author> result = underTest.findOne(author.getId());

        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }
}
