package edu.yacoubi.database.service;

import edu.yacoubi.database.model.entity.Author;

import java.util.List;

public interface IAuthorService {
    Author save(Author author);
    List<Author> getAll();
}
