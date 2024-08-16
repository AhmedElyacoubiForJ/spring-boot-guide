package edu.yacoubi.daocreateread_integration.dao;

import edu.yacoubi.daocreateread_integration.model.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> find(String isbn);
}
