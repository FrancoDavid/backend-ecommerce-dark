package com.darkdev.ecommerce.ecommerce_backend.controller;

import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiErrorResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.user.LoginRequestDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.user.UserDeleteResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.user.UserResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.model.User;
import com.darkdev.ecommerce.ecommerce_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<ObjectError>(
                    "Error validations", false, bindingResult.getAllErrors(), null), HttpStatus.BAD_REQUEST
            );
        }

        try {
            User userSaved = userService.register(user);
            UserResponseDTO userResponseDTO = new UserResponseDTO(userSaved.getEmail(), userSaved.getName(), userSaved.getRole());

            return new ResponseEntity<>(new ApiResponseDTO<UserResponseDTO>(true, "Register success", userResponseDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>(e.getMessage(), false, null, null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<ObjectError>(
                    "Error validations", false, bindingResult.getAllErrors(), null), HttpStatus.BAD_REQUEST
            );
        }

        try {
            User userSaved = userService.update(user);
            UserResponseDTO userResponseDTO = new UserResponseDTO(userSaved.getEmail(), userSaved.getName(), userSaved.getRole());

            return new ResponseEntity<>(new ApiResponseDTO<UserResponseDTO>(true, "Update success", userResponseDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>(e.getMessage(), false, null, null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Object> remove(@PathVariable Integer idUser){
        try {
            userService.remove(idUser);
            UserDeleteResponseDTO userDeleteResponseDTO = new UserDeleteResponseDTO(idUser);

            return new ResponseEntity<>(new ApiResponseDTO<UserDeleteResponseDTO>(true, "Remove success", userDeleteResponseDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>(e.getMessage(), false, null, null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<Object> profile(@PathVariable Integer idUser) {
        try {
            User userProfile = userService.detail(idUser);
            UserResponseDTO userResponseDTO = new UserResponseDTO(userProfile.getName(), userProfile.getEmail(), userProfile.getRole());

            return new ResponseEntity<>(new ApiResponseDTO<UserResponseDTO>(true, "Profile found", userResponseDTO), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Profile not found", false, null, null), HttpStatus.BAD_REQUEST);
        }
    }

}
