package edu.yacoubi.database.controller;

import edu.yacoubi.database.mapper.Mapper;
import edu.yacoubi.database.model.dto.BookDto;
import edu.yacoubi.database.model.entity.Book;
import edu.yacoubi.database.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;
    private final Mapper<Book, BookDto> bookMapper;

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createOrUpdateBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDto bookDto) {

        boolean exists = bookService.isExists(isbn);
        Book book = bookMapper.mapFrom(bookDto);
        Book updatedBook = bookService.createUpdateBook(isbn, book);

        if (exists) {
            return new ResponseEntity<>(bookMapper.mapTo(updatedBook), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bookMapper.mapTo(updatedBook), HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> books = bookService.getAll();
        return new ResponseEntity<>(
                books.stream()
                        .map(bookMapper::mapTo)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("isbn") String isbn) {
        Optional<Book> foundBook = bookService.getBook(isbn);

        return foundBook.map(book -> {
                    BookDto bookDto = bookMapper.mapTo(book);
                    return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
