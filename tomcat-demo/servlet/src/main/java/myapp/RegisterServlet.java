package myapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.HashSet;

@WebServlet("/api/register")
public class RegisterServlet extends HttpServlet {

    private static final Pattern VALID_PATTERN = Pattern.compile("^[a-zA-Z0-9]+$");
    private static final Set<String> VALID_ROLES = new HashSet<>();

    static {
        VALID_ROLES.add("designer");
        VALID_ROLES.add("user");
        VALID_ROLES.add("investor");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        response.setContentType("application/json");

        if (isInValid(username) || isInValid(password) || !isValidRole(role)) {
            Map<String, String> jsonResponse = Map.of("message", "Invalid input: Username and password must be alphanumeric, and role must be one of the predefined options.");
            JsonUtil.writeJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, jsonResponse);
            return;
        }

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password); // In a real application, make sure to hash the password
                statement.setString(3, email);
                statement.setString(4, role);
                statement.executeUpdate();
            }
            Map<String, String> jsonResponse = Map.of("message", "Registration successful!");
            JsonUtil.writeJsonResponse(response, HttpServletResponse.SC_OK, jsonResponse);
        } catch (SQLException e) {
            Map<String, String> jsonResponse = Map.of("message", "Registration failed due to a database error");
            JsonUtil.writeJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, jsonResponse);
        }
    }

    private boolean isInValid(String input) {
        return input == null || !VALID_PATTERN.matcher(input).matches();
    }

    private boolean isValidRole(String role) {
        return VALID_ROLES.contains(role);
    }
}