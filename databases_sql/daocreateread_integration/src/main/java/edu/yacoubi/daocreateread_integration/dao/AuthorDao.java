package edu.yacoubi.daocreateread_integration.dao;

import edu.yacoubi.daocreateread_integration.model.Author;

import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long id);
}
