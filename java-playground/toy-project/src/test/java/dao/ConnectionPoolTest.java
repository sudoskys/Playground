package dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {

    @Test
    void getConnection() {
        try {
            Connection connection = ConnectionPool.getConnection();
            assertNotNull(connection, "Connection should not be null");
            connection.close();
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}