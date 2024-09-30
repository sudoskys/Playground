package com.star.demo.controller;

import com.star.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StatusController {
    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/status")
    public Map<String, String> getStatus(HttpServletRequest request) {
        Map<String, String> response = new HashMap<>();
        String token = jwtUtil.extractTokenFromCookies(request);
        if (token != null && jwtUtil.validateToken(token)) {
            response.put("status", "valid");
            response.put("username", jwtUtil.extractUsername(token));
            logger.info("JWT is valid for user: {}", jwtUtil.extractUsername(token));
        } else {
            response.put("status", "invalid");
            logger.warn("Invalid JWT");
        }
        return response;
    }
}