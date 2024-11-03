package com.star.demo.service;

import com.star.demo.mapper.UserMapper;
import com.star.demo.model.User;
import com.star.demo.exception.UserNotFoundException;
import com.star.demo.exception.EmailAlreadyExistsException;
import com.star.demo.exception.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(String email, String password) {
        if (userMapper.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("邮箱已被注册");
        }
        User user = new User(email, passwordEncoder.encode(password));
        userMapper.insertUser(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public Optional<User> findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("用户不存在，ID: " + id));
    }

    @Transactional
    public User updateUser(Long id, User updateUser) {
        User existingUser = getUserById(id);
        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }
        
        // 更新基本信息
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setRole(updateUser.getRole());
        
        // 如果提供了新密码，则更新密码
        if (updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()) {
            // 使用 PasswordEncoder 加密新密码
            String encodedPassword = passwordEncoder.encode(updateUser.getPassword());
            existingUser.setPassword(encodedPassword);
        }
        
        userMapper.updateUser(existingUser);
        return existingUser;
    }

    @Transactional
    public void deleteUser(Long id) {
        if (userMapper.deleteUser(id) == 0) {
            throw new UserNotFoundException("用户不存在，ID: " + id);
        }
    }

    @Transactional
    public User changeUserRole(Long id, User.Role newRole) {
        User user = getUserById(id);
        user.setRole(newRole);
        userMapper.updateUser(user);
        return user;
    }

    public User authenticateUser(String email, String password) {
        User user = userMapper.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("用户不存在，邮箱: " + email));
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("密码不正确");
        }
        return user;
    }
}
