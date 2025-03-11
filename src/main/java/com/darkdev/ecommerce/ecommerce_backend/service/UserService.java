package com.darkdev.ecommerce.ecommerce_backend.service;

import com.darkdev.ecommerce.ecommerce_backend.model.User;
import com.darkdev.ecommerce.ecommerce_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.dao.DataIntegrityViolationException;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("User password wrong");
        }

        return user;
    }

    public User register(User user) throws Exception {
        try {
            return userRepository.save(user);

        } catch (DataIntegrityViolationException e) {
            throw new Exception("Email already exists");
        }

    }
}
