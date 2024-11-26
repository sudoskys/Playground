package myapp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import myapp.entities.User;
import myapp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class UserService {
    private static final Pattern VALID_PATTERN = Pattern.compile("^[a-zA-Z0-9]+$");
    private static final Set<String> VALID_ROLES = new HashSet<>();

    static {
        VALID_ROLES.add("designer");
        VALID_ROLES.add("user");
        VALID_ROLES.add("investor");
    }

    @Autowired
    private UserMapper userMapper;

    public void registerUser(User user) {
        userMapper.insert(user);
    }

    public User findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public static boolean validate(String username, String password, String role) {
        return isValidUsername(username) || isValidUsername(password) || !isValidRole(role);
    }

    private static boolean isValidUsername(String username) {
        return username == null || !VALID_PATTERN.matcher(username).matches();
    }

    private static boolean isValidRole(String role) {
        return VALID_ROLES.contains(role);
    }
}