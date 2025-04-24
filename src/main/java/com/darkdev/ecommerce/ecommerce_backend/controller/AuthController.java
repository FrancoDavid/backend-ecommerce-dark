package com.darkdev.ecommerce.ecommerce_backend.controller;

import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.user.LoginRequestDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.user.UserResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.exception.ValidationException;
import com.darkdev.ecommerce.ecommerce_backend.model.User;
import com.darkdev.ecommerce.ecommerce_backend.service.AuthService;
import com.darkdev.ecommerce.ecommerce_backend.utils.ExceptionUtils;
import com.darkdev.ecommerce.ecommerce_backend.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginDTO) {
        User user = authService.login(loginDTO.getEmail(), loginDTO.getPassword());
        String userToken = jwtUtils.generateToken(user.getEmail());

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                user.getName(),
                user.getEmail(),
                user.getRole(),
                userToken
        );

        return new ResponseEntity<>(new ApiResponseDTO<UserResponseDTO>(true, "Login success", userResponseDTO), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Error validations", ExceptionUtils.getErrorsFromBinding(bindingResult));
        }

        User userSaved = authService.register(user);
        String userToken = jwtUtils.generateToken(userSaved.getEmail());
        UserResponseDTO userResponseDTO = new UserResponseDTO(
                userSaved.getEmail(),
                userSaved.getName(),
                userSaved.getRole(),
                userToken
        );

        return new ResponseEntity<>(new ApiResponseDTO<UserResponseDTO>(true, "Register success", userResponseDTO), HttpStatus.OK);
    }
}
