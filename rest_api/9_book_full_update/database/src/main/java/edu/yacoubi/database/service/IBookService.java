package edu.yacoubi.database.service;

import edu.yacoubi.database.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Book createUpdateBook(String isbn, Book book);
    List<Book> getAll();
    Optional<Book> getBook(String isbn);
    boolean isExists(String isbn);
}
