package edu.yacoubi.book.controllers;

import edu.yacoubi.book.model.Book;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class BookController {
    @GetMapping(path = "/books")
    public Book getBook() {
        return Book.builder()
                .isbn("978-3-16-148410-0")
                .title("Clean Code")
                .author("Robert C. Martin")
                .publicationYear("2008")
                .build();
    }

    @PostMapping(path = "/books")
    public Book createBook(@RequestBody final Book book) {
        log.info("Received new book: " + book);
        return book;
    }
}
