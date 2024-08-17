package edu.yacoubi.daofindmany.dao;

import edu.yacoubi.daofindmany.model.Author;

import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long id);
}
