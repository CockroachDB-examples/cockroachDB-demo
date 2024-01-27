package com.example.cockroachdb;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class App implements CommandLineRunner {

    public static final String QUERY = "SELECT message FROM messages";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public App() {}

    App(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    public List<String> getResults() {
        return jdbcTemplate.query(QUERY, (rs, rowNum) -> rs.getString("message"));
    }

    @Override
    public void run(String... args) {
        System.out.println(getResults().getFirst());
    }
}
