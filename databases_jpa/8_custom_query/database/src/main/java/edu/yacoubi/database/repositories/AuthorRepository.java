package edu.yacoubi.database.repositories;

import edu.yacoubi.database.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> findByNameContaining(String authorA);
    Iterable<Author> ageLessThan(int age);
}
