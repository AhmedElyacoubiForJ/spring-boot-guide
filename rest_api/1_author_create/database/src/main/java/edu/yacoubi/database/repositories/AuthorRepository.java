package edu.yacoubi.database.repositories;

import edu.yacoubi.database.model.entities.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
}
