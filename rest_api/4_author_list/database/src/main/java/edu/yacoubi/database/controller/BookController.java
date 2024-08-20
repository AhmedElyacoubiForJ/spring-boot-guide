package edu.yacoubi.database.controller;

import edu.yacoubi.database.mapper.Mapper;
import edu.yacoubi.database.mapper.impl.BookMapperImpl;
import edu.yacoubi.database.model.dto.BookDto;
import edu.yacoubi.database.model.entity.Book;
import edu.yacoubi.database.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final IBookService  bookService;
    private final Mapper<Book, BookDto> bookMapper;

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        Book book = bookMapper.mapFrom(bookDto);
        Book savedBook = bookService.createBook(isbn, book);
        BookDto savedBookDto = bookMapper.mapTo(savedBook);

        return new ResponseEntity(savedBookDto, HttpStatus.CREATED);
    }
}
