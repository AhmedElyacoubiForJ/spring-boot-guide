package edu.yacoubi.database.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.yacoubi.database.TestDataUtil;
import edu.yacoubi.database.model.dto.BookDto;
import edu.yacoubi.database.model.entity.Book;
import edu.yacoubi.database.service.IBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final IBookService bookService;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc,
                                         ObjectMapper objectMapper,
                                         IBookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }

    @Test
    public void testThatCreateBookSuccessfullyReturnsHttp201Created() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        String jsonBook = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testThatCreateBookSuccessfullyReturnsSavedBook() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        String jsonBook = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook)
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
                );
    }

    @Test
    public void testThatGetAllBooksReturnsWithStatus200Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetAllBooksReturnsAllBooks() throws Exception {
        // Given
        Book bookA = TestDataUtil.createTestBookA(null);
        bookService.createUpdateBook(bookA.getIsbn(), bookA);

        Book bookB = TestDataUtil.createTestBookB(null);
        bookService.createUpdateBook(bookB.getIsbn(), bookB);

        Book bookC = TestDataUtil.createTestBookC(null);
        bookService.createUpdateBook(bookC.getIsbn(), bookC);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value(bookA.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(bookA.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].isbn").value(bookB.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value(bookB.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].isbn").value(bookC.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].title").value(bookC.getTitle()));
    }

    @Test
    public void testThatGetBookReturnsWithStatus200Ok() throws Exception {
        // Given
        Book bookA = TestDataUtil.createTestBookA(null);
        bookService.createUpdateBook(bookA.getIsbn(), bookA);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/{isbn}", bookA.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetBookReturnsWithStatus400NotFound() throws Exception {
        Book bookA = TestDataUtil.createTestBookA(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{isbn}", bookA.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetBookReturnsTheBook() throws Exception {
        // Given
        Book bookA = TestDataUtil.createTestBookA(null);
        bookService.createUpdateBook(bookA.getIsbn(), bookA);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{isbn}", bookA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(bookA.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookA.getTitle()));
    }

    @Test
    public void testThatUpdateBookReturnsHttpStatus200Ok() throws Exception {
        // Given
        Book bookA = TestDataUtil.createTestBookA(null);
        Book savedBookA = bookService.createUpdateBook(bookA.getIsbn(), bookA);

        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        bookDto.setIsbn(savedBookA.getIsbn());
        String jsonBook = objectMapper.writeValueAsString(bookDto);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + savedBookA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook)
                ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatUpdateBookReturnsTheUpdatedBook() throws Exception {
        // Given
        Book bookA = TestDataUtil.createTestBookA(null);
        Book savedBookA = bookService.createUpdateBook(bookA.getIsbn(), bookA);

        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        bookDto.setIsbn(savedBookA.getIsbn());
        bookDto.setTitle("UPDATED Title");
        String jsonBook = objectMapper.writeValueAsString(bookDto);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + savedBookA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
                );
    }

    @Test
    public void testThatPartialUpdateBookReturnsHttpStatus200Ok() throws Exception {
        // Given
        Book bookA = TestDataUtil.createTestBookA(null);
        Book savedBookA = bookService.createUpdateBook(bookA.getIsbn(), bookA);

        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        bookDto.setIsbn(savedBookA.getIsbn());
        bookDto.setTitle("UPDATED Title");
        String jsonBook = objectMapper.writeValueAsString(bookDto);

        JsonNode jsonNode = objectMapper.readTree(jsonBook);
        ObjectNode object = (ObjectNode) jsonNode;
        object.remove("id");
        object.remove("author");

        jsonBook = objectMapper.writeValueAsString(object);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.patch("/books/" + savedBookA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook)
                ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPartialUpdateBookRequestedWithTitleOnlyReturnsTheUpdatedBook() throws Exception {
        // Given
        Book bookA = TestDataUtil.createTestBookA(null);
        Book savedBookA = bookService.createUpdateBook(bookA.getIsbn(), bookA);

        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        bookDto.setTitle("UPDATED Title");
        String jsonBook = objectMapper.writeValueAsString(bookDto);

        JsonNode jsonNode = objectMapper.readTree(jsonBook);
        ObjectNode object = (ObjectNode) jsonNode;
        object.remove("id");
        object.remove("author");

        jsonBook = objectMapper.writeValueAsString(object);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.patch("/books/" + savedBookA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.isbn").value(savedBookA.getIsbn())
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.title").value("UPDATED Title")
                );
    }
}
