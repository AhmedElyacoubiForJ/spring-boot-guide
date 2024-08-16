package edu.yacoubi.daoreadone.dao.impl;

import edu.yacoubi.daoreadone.dao.BookDao;
import edu.yacoubi.daoreadone.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId()
        );
    }
}
