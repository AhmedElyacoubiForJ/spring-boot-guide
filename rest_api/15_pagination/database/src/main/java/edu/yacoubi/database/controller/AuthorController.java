package edu.yacoubi.database.controller;

import edu.yacoubi.database.mapper.Mapper;
import edu.yacoubi.database.model.dto.AuthorDto;
import edu.yacoubi.database.model.entity.Author;
import edu.yacoubi.database.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
        List<Author> authors = authorService.getAll();

        return new ResponseEntity<>(
                authors.stream()
                        .map(authorMapper::mapTo)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id) {
        Optional<Author> authorOptional = authorService.getAuthor(id);

        return authorOptional.map(author -> {
            AuthorDto authorDto = authorMapper.mapTo(author);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(
            @PathVariable("id") Long id,
            @RequestBody AuthorDto authorDto) {

        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorDto.setId(id);
        Author author = authorMapper.mapFrom(authorDto);
        Author updatedAuthor = authorService.save(author);

        return new ResponseEntity<>(authorMapper.mapTo(updatedAuthor), HttpStatus.OK);
    }

    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> partialUpdateAuthor(
            @PathVariable("id") Long id,
            @RequestBody AuthorDto authorDto) {

        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Author author = authorMapper.mapFrom(authorDto);
        Author updatedAuthor = authorService.partialUpdate(id, author);

        return new ResponseEntity<>(authorMapper.mapTo(updatedAuthor), HttpStatus.OK);
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long id) {
        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
