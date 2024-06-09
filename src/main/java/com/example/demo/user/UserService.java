package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    private final int SALT_LENGTH = 5;

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
