package edu.yacoubi.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.yacoubi.book.model.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonTest {
    @Test
    public void testThatObjectMapperCanCreateJsonFromJavaObject() throws JsonProcessingException {
        // Given
        Book book = Book.builder()
                .isbn("978-3-16-148410-0")
                .title("Clean Code: A Handbook of Agile Software Craftsmanship")
                .author("Robert C. Martin")
                .publicationYear("2008")
                .build();

        // When
        String json = new ObjectMapper().writeValueAsString(book);

        // Then
        assertThat(json).isEqualTo("{\"isbn\":\"978-3-16-148410-0\",\"title\":\"Clean Code: A Handbook of Agile Software Craftsmanship\",\"author\":\"Robert C. Martin\",\"publicationYear\":\"2008\"}");
    }
}
