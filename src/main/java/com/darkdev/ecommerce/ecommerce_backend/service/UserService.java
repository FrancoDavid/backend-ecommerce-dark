package com.darkdev.ecommerce.ecommerce_backend.service;

import com.darkdev.ecommerce.ecommerce_backend.model.User;
import com.darkdev.ecommerce.ecommerce_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;


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

    public User update(User user) throws Exception {
        try {
            return userRepository.save(user);

        } catch (DataIntegrityViolationException e) {
            throw new Exception("Email already exists");
        }

    }

    public void remove(Integer idUser) throws Exception {
        try {
            userRepository.deleteById(idUser);
        } catch (Exception e) {
            throw new Exception("Email not found");
        }
    }

    public User detail(Integer idUser) throws Exception {
        try {
            return userRepository.findById(idUser)
                    .orElseThrow(() -> new Exception("Profile not found"));
        } catch (Exception e) {
            throw new Exception("Profile not found");
        }
    }

    public List<User> users() throws Exception {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Users not found");
        }
    }

    public User searchByEmail(String email)  throws RuntimeException {
        try {
            return userRepository.findByEmail(email);
        } catch (RuntimeException e) {
            throw  new RuntimeException("User not found: " + e.getMessage());
        }

    }
}
