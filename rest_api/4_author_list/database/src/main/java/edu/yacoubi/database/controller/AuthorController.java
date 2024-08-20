package edu.yacoubi.database.controller;

import edu.yacoubi.database.mapper.Mapper;
import edu.yacoubi.database.model.dto.AuthorDto;
import edu.yacoubi.database.model.entity.Author;
import edu.yacoubi.database.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final IAuthorService authorService;
    private final Mapper<Author, AuthorDto> authorMapper;

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        Author author = authorMapper.mapFrom(authorDto);
        Author savedAuthor = authorService.save(author);
        AuthorDto savedAuthorDto = authorMapper.mapTo(savedAuthor);

        return new ResponseEntity<>(savedAuthorDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/authors")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<Author> authors = authorService.findAll();

        return new ResponseEntity<>(
                authors.stream()
                        .map(authorMapper::mapTo)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
}
