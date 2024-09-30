package com.star.demo.controller;

import com.star.demo.model.User;
import com.star.demo.security.JwtUtil;
import com.star.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, String> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        Map<String, String> response = new HashMap<>();
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                response.put(error.getField(), error.getDefaultMessage());
            }
            return response;
        }
        userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getRole());
        response.put("message", "User registered successfully");
        return response;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        Map<String, String> response = new HashMap<>();
        User user = userService.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            response.put("token", jwtUtil.generateToken(username));
        } else {
            response.put("error", "Invalid username or password");
        }
        return response;
    }
}