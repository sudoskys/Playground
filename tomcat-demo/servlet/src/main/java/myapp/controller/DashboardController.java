package myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/protected")
public class DashboardController {

    @GetMapping("/dashboard")
    public String getDashboard(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        if (username != null) {
            return "Welcome to the protected dashboard, " + username;
        } else {
            return "Unauthorized access!";
        }
    }
}