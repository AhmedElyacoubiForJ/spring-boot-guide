package edu.yacoubi.daodelete.dao;

import edu.yacoubi.daodelete.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long id);

    List<Author> findAll();

    void update(long id, Author author);

    void delete(long id);
}
