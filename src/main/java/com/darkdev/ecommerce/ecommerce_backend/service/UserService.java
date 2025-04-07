package com.darkdev.ecommerce.ecommerce_backend.service;

import com.darkdev.ecommerce.ecommerce_backend.exception.BadRequestException;
import com.darkdev.ecommerce.ecommerce_backend.exception.NotFoundException;
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
            throw new NotFoundException("User not found", "User don t exists");
        }

        if (!user.getPassword().equals(password)) {
            throw new BadRequestException("User password wrong", "User don t pass");
        }

        return user;
    }

    public User register(User user) {
        try {
            return userRepository.save(user);

        } catch (Exception e) {
            throw new RuntimeException("Email already exists");
        }
    }

    public User update(User user) {
        try {
            return userRepository.save(user);

        } catch (RuntimeException e) {
            throw new RuntimeException("Email already exists");
        }

    }

    public void remove(Integer idUser) {
        try {
            userRepository.deleteById(idUser);
        } catch (Exception e) {
            throw new RuntimeException("Email not found");
        }
    }

    public User detail(Integer idUser) {
        try {
            return userRepository.findById(idUser)
                    .orElseThrow(() -> new Exception("Profile not found"));
        } catch (Exception e) {
            throw new RuntimeException("Profile not found");
        }
    }

    public List<User> users() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Users not found");
        }
    }

    public User searchByEmail(String email)  {
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            throw  new RuntimeException("User not found");
        }
    }
}
