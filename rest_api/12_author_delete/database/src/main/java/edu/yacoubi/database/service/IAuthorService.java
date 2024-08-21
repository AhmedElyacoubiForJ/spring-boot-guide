package edu.yacoubi.database.service;

import edu.yacoubi.database.model.entity.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    Author save(Author author);
    List<Author> getAll();
    Optional<Author> getAuthor(Long id);
    boolean isExists(Long id);
    Author partialUpdate(Long id, Author author);
    void delete(Long id);
}
