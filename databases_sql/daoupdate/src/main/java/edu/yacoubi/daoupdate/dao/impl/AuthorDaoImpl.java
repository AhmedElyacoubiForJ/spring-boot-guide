package edu.yacoubi.daoupdate.dao.impl;

import edu.yacoubi.daoupdate.dao.AuthorDao;
import edu.yacoubi.daoupdate.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void create(Author author) {
        jdbcTemplate.update(
                "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                author.getId(),
                author.getName(),
                author.getAge()
        );
    }

    @Override
    public Optional<Author> findOne(long authorId) {
        List<Author> results = jdbcTemplate.query(
                "SELECT id, name, age FROM authors WHERE id =? LIMIT 1",
                new AuthorRowMapper(),
                authorId
        );
        return results.stream().findFirst();
    }

    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, age FROM authors",
                new AuthorRowMapper());
    }

    @Override
    public void update(Author author) {
        jdbcTemplate.update(
                "UPDATE authors SET id=?, name =?, age =? WHERE id =?",
                author.getId(), author.getName(), author.getAge(), author.getId()
        );
    }

    // RowMapper implementation
    public static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
