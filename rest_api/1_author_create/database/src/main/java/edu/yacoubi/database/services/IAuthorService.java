package edu.yacoubi.database.services;

import edu.yacoubi.database.model.entities.AuthorEntity;

public interface IAuthorService {
    AuthorEntity save(AuthorEntity authorEntity);
}
