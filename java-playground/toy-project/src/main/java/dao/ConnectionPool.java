package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgresUser");
        config.setPassword("postgresPW");
        config.setMaximumPoolSize(10);
        ds = new HikariDataSource(config);
    }

    private ConnectionPool() {
    }

    /**
     * Returns a connection to the database
     *
     * @return Connection to the database
     * @throws SQLException if a database access error occurs
     * @see Connection
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}