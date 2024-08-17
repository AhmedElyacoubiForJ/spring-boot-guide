package edu.yacoubi.daofindmany.dao;

import edu.yacoubi.daofindmany.model.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> find(String isbn);
}
