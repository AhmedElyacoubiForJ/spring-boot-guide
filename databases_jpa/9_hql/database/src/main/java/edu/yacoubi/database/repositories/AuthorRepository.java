package edu.yacoubi.database.repositories;

import edu.yacoubi.database.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> findByNameContaining(String authorA);
    Iterable<Author> ageLessThan(int age);

    @Query("SELECT a FROM Author a WHERE age > ?1")
    Iterable<Author> findAuthorsAgeGreaterThan(int age);
}
