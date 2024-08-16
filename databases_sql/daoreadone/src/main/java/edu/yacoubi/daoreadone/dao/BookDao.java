package edu.yacoubi.daoreadone.dao;

import edu.yacoubi.daoreadone.model.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> find(String isbn);
}
