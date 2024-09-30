package com.star.demo.service;

import com.star.demo.mapper.UserMapper;
import com.star.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(String username, String password, String email, String role) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setEmail(email);
        user.setRole(role);
        userMapper.insertUser(user);
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}