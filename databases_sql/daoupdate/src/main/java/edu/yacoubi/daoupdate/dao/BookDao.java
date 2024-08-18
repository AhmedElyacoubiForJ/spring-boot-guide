package edu.yacoubi.daofindmany.dao;

import edu.yacoubi.daofindmany.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> findAll();
}
