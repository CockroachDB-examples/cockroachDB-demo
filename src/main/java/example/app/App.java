package example.app;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import org.postgresql.ds.PGSimpleDataSource;

public class App {

    private static final String JDBC_DATABASE_URL = "jdbc:postgresql://%s:%s/%s?sslmode=verify-full&password=%s&user=%s";

    public static void executeStmt(final Connection conn) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT message FROM messages");

            while (!rs.wasNull() && rs.next()) {
                System.out.println(rs.getString(1));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            PGSimpleDataSource ds = new PGSimpleDataSource();
            ds.setApplicationName("docs_quickstart_java");
            ds.setUrl(JDBC_DATABASE_URL);
            Connection connection = ds.getConnection();
            executeStmt(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
