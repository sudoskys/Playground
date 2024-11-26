package myapp.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import myapp.ConfigUtil;
import myapp.entities.User;
import myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import java.util.UUID;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private static final String SECRET = ConfigUtil.getProperty("jwt.secret");

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String role) {
        if (UserService.validate(username, password, role)) {
            return ResponseEntity.status(400).body(Map.of("message", "Invalid input."));
        }
        // Generate unique id
        Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        // Register user
        User user = new User(id, username, password, email, role);
        try {
            userService.registerUser(user);
            return ResponseEntity.ok(Map.of("message", "Registration successful!"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Registration failed."));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        User user = userService.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) { // In real application, compare hashed passwords
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = JWT.create().withIssuer("auth0").withClaim("username", username).withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)).sign(algorithm);

            Cookie cookie = new Cookie("token", token);
            // cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            System.out.println("Login successful!");
            response.addCookie(cookie);
            return ResponseEntity.ok(Map.of("message", "Login successful!", "token", token));
        }
        return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password."));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok("Logout successful!");
    }
}