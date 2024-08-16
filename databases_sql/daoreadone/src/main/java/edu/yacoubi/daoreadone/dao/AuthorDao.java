package edu.yacoubi.daoreadone.dao;

import edu.yacoubi.daoreadone.model.Author;

import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long id);
}
