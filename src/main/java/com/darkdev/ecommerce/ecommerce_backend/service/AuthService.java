package com.darkdev.ecommerce.ecommerce_backend.service;

import com.darkdev.ecommerce.ecommerce_backend.exception.BadRequestException;
import com.darkdev.ecommerce.ecommerce_backend.exception.NotFoundException;
import com.darkdev.ecommerce.ecommerce_backend.model.User;
import com.darkdev.ecommerce.ecommerce_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new NotFoundException("User not found", "User don t exists");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("User password wrong", "User don t pass");
        }

        return user;
    }

    public User register(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);

        } catch (Exception e) {
            throw new RuntimeException("Email already exists");
        }
    }
}
