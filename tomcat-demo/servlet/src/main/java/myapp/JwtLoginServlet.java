package myapp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/login")
public class JwtLoginServlet extends HttpServlet {

    private static final String SECRET = ConfigUtil.getProperty("jwt.secret");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT password FROM users WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String storedPassword = resultSet.getString("password");
                        if (storedPassword.equals(password)) { // 在实际应用中，请确保对密码进行哈希处理
                            Algorithm algorithm = Algorithm.HMAC256(SECRET);
                            String token = JWT.create().withIssuer("auth0").withClaim("username", username).withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 hour expiration
                                    .sign(algorithm);

                            // 将 JWT 添加到 cookie 中
                            Cookie cookie = new Cookie("token", token);
                            // cookie.setSecure(true);
                            // cookie.setHttpOnly(true);
                            cookie.setPath("/");
                            cookie.setMaxAge(3600); // 1 hour expiration
                            response.addCookie(cookie);

                            Map<String, String> jsonResponse = new HashMap<>();
                            jsonResponse.put("message", "Login successful!");
                            jsonResponse.put("token", token);

                            JsonUtil.writeJsonResponse(response, HttpServletResponse.SC_OK, jsonResponse);
                            return;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Map<String, String> jsonResponse = new HashMap<>();
            jsonResponse.put("message", "Login failed due to a database error");
            JsonUtil.writeJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, jsonResponse);
            return;
        }
        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("message", "Invalid username or password!");
        JsonUtil.writeJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED, jsonResponse);
    }
}