package edu.yacoubi.database.repository;

import edu.yacoubi.database.model.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> findByNameContaining(String authorA);
    Iterable<Author> ageLessThan(int age);

    @Query("SELECT a FROM Author a WHERE age > ?1")
    Iterable<Author> findAuthorsAgeGreaterThan(int age);
}
