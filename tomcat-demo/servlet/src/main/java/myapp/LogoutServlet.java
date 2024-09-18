package myapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the JWT cookie by setting its max age to 0
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/"); // Ensure the path matches the path where the cookie is set
        cookie.setHttpOnly(true); // Secure the cookie
        cookie.setSecure(true); // If using HTTPS, ensure the Secure flag is set
        cookie.setMaxAge(0); // Invalidate cookie immediately

        response.addCookie(cookie);

        response.getWriter().println("Logout successful!");
    }
}