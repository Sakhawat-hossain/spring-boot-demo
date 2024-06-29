package com.example.demo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    private final int SALT_LENGTH = 5;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setRole("ADMIN");
        user.setStatus(1);

        String password = request.getPassword();
        String salt = getSalt();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(salt + password);

        user.setPassword(encodedPassword);
        user.setSalt(salt);

        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User authenticateUser(String username, String password) {
        if (username == null || password == null) {
            logger.debug("Username or password is null");
            return null;
        }

        User user = userRepository.findByEmail(username);
        if (user == null) {
            logger.debug("User not found");
            return null;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches((user.getSalt() + password), user.getPassword())) {
            logger.debug("Password not matched");
            return null;
        }

        return user;
    }

    private String getSalt() {
        String salt = "";
        String chars = "0123456789"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz";

        Random random = new Random();
        for (int i = 0; i < SALT_LENGTH; i++) {
            salt += chars.charAt(random.nextInt(62));
        }

        return salt;
    }
}
