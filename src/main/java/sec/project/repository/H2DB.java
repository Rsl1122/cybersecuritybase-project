package sec.project.repository;

import org.h2.jdbcx.JdbcDataSource;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class H2DB implements AutoCloseable {

    private String dbFilePath;
    private Connection connection;

    public H2DB() {
        dbFilePath = new File("database").getAbsolutePath();
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) connection = getNewConnection();
        return connection;
    }

    private Connection getNewConnection() throws SQLException {
        String username = "user";
        String password = "password";

        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:file:" + dbFilePath + ";mode=MySQL");
        jdbcDataSource.setUser(username);
        jdbcDataSource.setPassword(password);

        return jdbcDataSource.getConnection();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) connection.close();
    }
}
