package edu.yacoubi.database.service;

import edu.yacoubi.database.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Book createUpdateBook(String isbn, Book book);
    List<Book> getAll();
    Page<Book> getAll(Pageable pageable);
    Optional<Book> getBook(String isbn);
    boolean isExists(String isbn);
    Book partialUpdate(String isbn, Book book);
    void delete(String isbn);
}
