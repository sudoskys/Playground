package com.star.demo.controller;

import com.star.demo.annotation.RequireRole;
import com.star.demo.model.User;
import com.star.demo.security.JwtUtil;
import com.star.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final List<String> ADMIN_ROLES = List.of("ADMIN");

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    private boolean isAdmin(HttpServletRequest request) {
        String token = jwtUtil.extractTokenFromCookies(request).orElse(null);
        if (token == null) {
            return false;
        }
        String role = jwtUtil.extractRole(token);
        return ADMIN_ROLES.contains(role);
    }

    @GetMapping
    @RequireRole("ADMIN")
    public ResponseEntity<?> getAllUsers() {
        logger.info("管理员获取所有用户信息");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @RequireRole("ADMIN")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            logger.warn("尝试获取不存在的用户，ID：{}", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("管理员获取用户信息，用户ID：{}", id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, HttpServletRequest request) {
        if (!isAdmin(request)) {
            logger.warn("非管理员尝试创建用户");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "访问被拒绝"));
        }
        try {
            userService.registerUser(user.getEmail(), user.getPassword());
            logger.info("管理员成功创建用户：{}", user.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "用户创建成功"));
        } catch (Exception e) {
            logger.error("创建用户过程中发生错误", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "创建用户时发生错误"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User user, HttpServletRequest request) {
        if (!isAdmin(request)) {
            logger.warn("非管理员尝试更新用户，用户ID：{}", id);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "访问被拒绝"));
        }
        try {
            userService.updateUser(id, user);
            logger.info("管理员成功更新用户，用户ID：{}", id);
            return ResponseEntity.ok(Map.of("message", "用户更新成功"));
        } catch (Exception e) {
            logger.error("更新用户过程中发生错误，用户ID：{}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "更新用户时发生错误"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            logger.warn("非管理员尝试删除用户，用户ID：{}", id);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "访问被拒绝"));
        }
        try {
            userService.deleteUser(id);
            logger.info("管理员成功删除用户，用户ID：{}", id);
            return ResponseEntity.ok(Map.of("message", "用户删除成功"));
        } catch (Exception e) {
            logger.error("删除用户过程中发生错误，用户ID：{}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "删除用户时发生错误"));
        }
    }
}
