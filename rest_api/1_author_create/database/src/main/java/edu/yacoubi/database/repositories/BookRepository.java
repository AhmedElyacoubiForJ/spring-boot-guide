package edu.yacoubi.database.repositories;

import edu.yacoubi.database.model.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, String> {
}
