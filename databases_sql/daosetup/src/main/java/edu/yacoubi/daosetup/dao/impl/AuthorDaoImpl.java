package edu.yacoubi.daosetup.dao.impl;

import edu.yacoubi.daosetup.dao.AuthorDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;
}
