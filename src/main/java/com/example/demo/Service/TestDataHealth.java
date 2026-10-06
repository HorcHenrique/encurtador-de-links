package com.example.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class TestDataHealth {

private final JdbcTemplate jdbcTemplate;

public TestDataHealth(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}

public boolean isDataBaseConnected() {

    try {
        jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        return true;
    } catch (Exception e) {
        return false;
    }

}

}
