package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        Properties props = new Properties();
        try (InputStream is = ConnectionPool.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (is != null) {
                props.load(is);
                config.setJdbcUrl(props.getProperty("jdbcUrl"));
                config.setUsername(props.getProperty("username"));
                config.setPassword(props.getProperty("password"));
            } else {
                throw new RuntimeException("Cannot find db.properties file");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load db.properties file", e);
        }
        config.setMaximumPoolSize(10);
        ds = new HikariDataSource(config);
    }

    private ConnectionPool() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}