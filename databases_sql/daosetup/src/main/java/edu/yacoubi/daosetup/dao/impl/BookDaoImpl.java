package edu.yacoubi.daosetup.dao.impl;

import edu.yacoubi.daosetup.dao.BookDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;
}
