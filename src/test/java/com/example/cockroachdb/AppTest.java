package com.example.cockroachdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AppTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void canGetMessagesFromDatabase() {
        final List<String> results = new App(jdbcTemplate).getResults();
        assertEquals(3, results.size());
        assertEquals("Successfully Created Table, created a row and read it back!", results.getFirst());
    }
}
