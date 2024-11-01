package com.star.demo.controller;

import com.star.demo.model.User;
import com.star.demo.security.JwtUtil;
import com.star.demo.service.UserService;
import com.star.demo.common.ApiResponse;
import com.star.demo.dto.request.LoginRequest;
import com.star.demo.dto.response.AuthResponse;
import com.star.demo.dto.response.UserResponse;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody User user) {
        log.info("收到注册请求：{}", user.getEmail());
        User registeredUser = userService.registerUser(user.getEmail(), user.getPassword());
        String token = jwtUtil.generateToken(
            registeredUser.getEmail(), 
            registeredUser.getId(), 
            registeredUser.getRole().toString()
        );
        
        return ApiResponse.success(AuthResponse.builder()
            .token(token)
            .user(UserResponse.fromUser(registeredUser))
            .build());
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(
            @Valid @RequestBody LoginRequest loginRequest, 
            HttpServletResponse response) {
        log.info("收到登录请求：{}", loginRequest.getEmail());
        
        User user = userService.authenticateUser(
            loginRequest.getEmail(), 
            loginRequest.getPassword()
        );
        
        String token = jwtUtil.generateToken(
            user.getEmail(), 
            user.getId(), 
            user.getRole().toString()
        );

        return ApiResponse.success(AuthResponse.builder()
            .token(token)
            .user(UserResponse.fromUser(user))
            .build());
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout() {
        return ApiResponse.success(null);
    }

    @GetMapping("/user")
    public ApiResponse<UserResponse> getCurrentUser(
            @RequestHeader("Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        User user = userService.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("用户不存在"));
        
        return ApiResponse.success(UserResponse.fromUser(user));
    }
    /**
     * 续签 Token
     * @param token
     * @return
     */
    @PostMapping("/ping")
    public ApiResponse<AuthResponse> ping(
            @RequestHeader("Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        User user = userService.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("用户不存在"));
        
        String newToken = jwtUtil.generateToken(
            email, 
            user.getId(), 
            user.getRole().toString()
        );
        
        return ApiResponse.success(AuthResponse.builder()
            .token(newToken)
            .user(UserResponse.fromUser(user))
            .build());
    }
}
