package com.example.cockroachdb;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void canGetMessagesFromDatabase() {
        final List<String> results = new App().getResults();
        assertEquals(1, results.size());
        assertEquals("Successfully Created Table, created a row and read it back!", results.getFirst());
    }
}
