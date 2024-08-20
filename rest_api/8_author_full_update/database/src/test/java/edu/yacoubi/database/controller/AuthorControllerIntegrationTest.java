package edu.yacoubi.database.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.yacoubi.database.TestDataUtil;
import edu.yacoubi.database.model.entity.Author;
import edu.yacoubi.database.service.IAuthorService;
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
public class AuthorControllerIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final IAuthorService authorService;

    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper,
                                           IAuthorService authorService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.authorService = authorService;
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        Author testAuthor = TestDataUtil.createTestAuthorA();
        testAuthor.setId(null);
        String jsonAuthor = objectMapper.writeValueAsString(testAuthor);

        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAuthor)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        Author testAuthor = TestDataUtil.createTestAuthorA();
        testAuthor.setId(null);
        String jsonAuthor = objectMapper.writeValueAsString(testAuthor);

        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAuthor)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("John Wayne")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(78)
        );
    }

    @Test
    public void testThatGetAllAuthorsReturnsWithStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetAllAuthorsReturnsListOfAuthors() throws Exception {
        // Given
        Author testAuthorA = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorA);

        Author testAuthorB = TestDataUtil.createTestAuthorB();
        authorService.save(testAuthorB);

        Author testAuthorC = TestDataUtil.createTestAuthorC();
        authorService.save(testAuthorC);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(testAuthorA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(testAuthorA.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(testAuthorB.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value(testAuthorB.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value(testAuthorC.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].age").value(testAuthorC.getAge()));
    }

    @Test
    public void testThatGetAuthorByIdReturnsWithStatus200Ok() throws Exception {
        Author testAuthor = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthor);

        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", testAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetAuthorByIdReturnsWithStatus400NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", -1)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetAuthorByIdReturnsAuthor() throws Exception {
        Author testAuthor = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthor);

        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", testAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testAuthor.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testAuthor.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(testAuthor.getAge()));
    }

//    @Test
//    public void testThatCreateAuthorWithInvalidJsonReturnsHttp400BadRequest() throws Exception {}
}
