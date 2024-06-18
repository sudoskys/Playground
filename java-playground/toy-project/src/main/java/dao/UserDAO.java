package dao;

import entity.User;
import service.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    /**
     * 添加用户
     *
     * @param user 用户对象
     * @throws SQLException SQL异常
     */
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, score) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionPool.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getScore());
            stmt.executeUpdate();
        }
    }

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     * @throws SQLException SQL异常
     */
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ?, score = ? WHERE id = ?";
        try (Connection conn = ConnectionPool.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getScore());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed to update user: " + e.getMessage());
            throw e;
        }
    }

    /**
     * 更新用户积分
     *
     * @param userId   用户ID
     * @param newScore 新的积分
     * @throws SQLException SQL异常
     */
    public void updateUserScore(int userId, int newScore) throws SQLException {
        String sql = "UPDATE users SET score = ? WHERE id = ?";
        try (Connection conn = ConnectionPool.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newScore);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        }
    }

    /**
     * 根据用户ID删除用户
     *
     * @param userId 用户ID
     * @throws SQLException SQL异常
     */
    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = ConnectionPool.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户对象
     * @throws SQLException SQL异常
     */
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = ConnectionPool.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getInt("score"));
                }
            }
        }
        return null;
    }

    /**
     * 根据用户ID获取用户
     *
     * @return 用户对象
     * @throws SQLException SQL异常
     */
    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection conn = ConnectionPool.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getInt("score")));
            }
        }
        return users;
    }

    /**
     * 根据用户名搜索用户
     *
     * @param username 用户名
     * @return 用户对象列表
     * @throws SQLException SQL异常
     */
    public List<User> searchUsersByUsername(String username) throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username LIKE ?")) {
            ps.setString(1, "%" + username + "%"); // 这里使用了模糊查询，所以在前后加上 %
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String usernameRead = rs.getString("username");
                String password = rs.getString("password");
                int score = rs.getInt("score");
                users.add(new User(id, usernameRead, password, score));
            }
        }
        return users;
    }

    public boolean testConnection() {
        try {
            Connection conn = ConnectionPool.getConnection();
            return true;
        } catch (Exception e) {
            logger.warning("Failed to test connection: " + e.getMessage());
            return false;
        }
    }
}