package com.darkdev.ecommerce.ecommerce_backend.controller;

import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.user.LoginRequestDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.user.UserResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.model.User;
import com.darkdev.ecommerce.ecommerce_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginDTO) {
        try {
            User user = userService.login(loginDTO.getEmail(), loginDTO.getPassword());

            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    user.getName(),
                    user.getEmail(),
                    user.getRole()
            );

            return new ResponseEntity<>(new ApiResponseDTO<UserResponseDTO>(true, "Login success", userResponseDTO), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ApiResponseDTO<Object>(false, e.getMessage(), null), HttpStatus.UNAUTHORIZED);
        }
    }
}
