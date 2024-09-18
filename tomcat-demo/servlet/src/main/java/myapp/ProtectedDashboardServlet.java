package myapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/protected/dashboard")
public class ProtectedDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = (String) request.getAttribute("username");

        if (username != null) {
            response.getWriter().println("Welcome to the protected dashboard, " + username);
        } else {
            response.getWriter().println("Unauthorized access!");
        }
    }
}