package com.star.demo.controller;

import com.star.demo.model.User;
import com.star.demo.security.JwtUtil;
import com.star.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.Cookie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, String> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        Map<String, String> response = new HashMap<>();
        try {
            if (bindingResult.hasErrors()) {
                for (FieldError error : bindingResult.getFieldErrors()) {
                    logger.warn("Validation error on field {}: {}", error.getField(), error.getDefaultMessage());
                    response.put(error.getField(), error.getDefaultMessage());
                }
                return response;
            }
            userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getRole());
            response.put("message", "User registered successfully");
            logger.info("User registered successfully: {}", user.getUsername());
        } catch (Exception e) {
            logger.error("An error occurred during registration", e);
            response.put("message", "An error occurred during registration");
        }
        return response;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest, HttpServletResponse response) {
        Map<String, String> responseBody = new HashMap<>();
        try {
            if (!loginRequest.containsKey("username") || !loginRequest.containsKey("password")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                responseBody.put("message", "Username and password are required");
                return responseBody;
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseBody.put("message", "Invalid request body");
            return responseBody;
        }
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        User user = userService.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtUtil.generateToken(username);
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.setStatus(HttpServletResponse.SC_OK);
            responseBody.put("message", "Login successful");
            logger.info("User logged in successfully: {}", username);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            responseBody.put("message", "Invalid username or password");
            logger.warn("Failed login attempt for username: {}", username);
        }
        return responseBody;
    }
}