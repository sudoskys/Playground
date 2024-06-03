package week07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class PostgresJDBCExample {

    public static void main(String[] args) {
        // 数据库连接信息
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgresUser";
        String password = "postgresPW";
        // 连接对象
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = DriverManager.getConnection(url, user, password);

            // 创建Statement对象
            stmt = conn.createStatement();

            // 执行查询
            String sql = "SELECT id, name FROM your_table";
            rs = stmt.executeQuery(sql);

            // 处理结果集
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}