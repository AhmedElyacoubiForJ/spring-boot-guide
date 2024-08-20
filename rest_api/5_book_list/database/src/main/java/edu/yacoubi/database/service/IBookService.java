package edu.yacoubi.database.service;

import edu.yacoubi.database.model.entity.Book;

import java.util.List;

public interface IBookService {
    Book createBook(String isbn, Book book);

    List<Book> getAll();
}
