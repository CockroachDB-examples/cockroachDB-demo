package com.example.cockroachdb;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.ds.PGSimpleDataSource;

public class App {

    private static final String JDBC_DATABASE_URL = "jdbc:postgresql://%s:%s/%s?sslmode=verify-full&password=%s&user=%s";



    public static void main(String[] args) {
        System.out.println(new App().getResults());
    }

    public List<String> getResults() {
        try {
            PGSimpleDataSource ds = new PGSimpleDataSource();
            ds.setApplicationName("docs_quickstart_java");
            ds.setUrl(String.format(JDBC_DATABASE_URL,
                    System.getProperty("cockroachDBCluster"),
                    System.getProperty("cockroachDBPort"),
                    System.getProperty("cockroachDBDatabase"),
                    System.getProperty("cockroachDBPassword"),
                    System.getProperty("cockroachDBUser")));
            Connection connection = ds.getConnection();
            return executeStmt(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> executeStmt(final Connection conn) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT message FROM messages");
            final List<String> results = new ArrayList<>();

            while (rs.next()) {
                results.add(rs.getString(1));
            }
            rs.close();
            st.close();
            return results;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
