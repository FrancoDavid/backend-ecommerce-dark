package com.darkdev.ecommerce.ecommerce_backend.controller;

import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiErrorResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.user.LoginRequestDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.user.UserDeleteResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.user.UserResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.exception.ValidationException;
import com.darkdev.ecommerce.ecommerce_backend.model.User;
import com.darkdev.ecommerce.ecommerce_backend.service.UserService;
import com.darkdev.ecommerce.ecommerce_backend.utils.ExceptionUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginDTO) {
            User user = userService.login(loginDTO.getEmail(), loginDTO.getPassword());

            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    user.getName(),
                    user.getEmail(),
                    user.getRole()
            );

            return new ResponseEntity<>(new ApiResponseDTO<UserResponseDTO>(true, "Login success", userResponseDTO), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Error validations", ExceptionUtils.getErrorsFromBinding(bindingResult));
        }

        User userSaved = userService.register(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO(userSaved.getEmail(), userSaved.getName(), userSaved.getRole());

        return new ResponseEntity<>(new ApiResponseDTO<UserResponseDTO>(true, "Register success", userResponseDTO), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Error validations", ExceptionUtils.getErrorsFromBinding(bindingResult));
        }

        User userSaved = userService.update(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO(userSaved.getEmail(), userSaved.getName(), userSaved.getRole());

        return new ResponseEntity<>(new ApiResponseDTO<UserResponseDTO>(true, "Update success", userResponseDTO), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{idUser}")
    public ResponseEntity<Object> remove(@PathVariable Integer idUser){
        userService.remove(idUser);
        UserDeleteResponseDTO userDeleteResponseDTO = new UserDeleteResponseDTO(idUser);

        return new ResponseEntity<>(new ApiResponseDTO<UserDeleteResponseDTO>(true, "Remove success", userDeleteResponseDTO), HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{idUser}")
    public ResponseEntity<Object> profile(@PathVariable Integer idUser) {
        User userProfile = userService.detail(idUser);
        UserResponseDTO userResponseDTO = new UserResponseDTO(userProfile.getName(), userProfile.getEmail(), userProfile.getRole());

        return new ResponseEntity<>(new ApiResponseDTO<UserResponseDTO>(true, "Profile found", userResponseDTO), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/all")
    public ResponseEntity<Object> users() {
        List<User> userList = userService.users();
        List<UserResponseDTO> userResponseDTOList = userList.stream()
                .map(user -> new UserResponseDTO(user.getName(), user.getEmail(), user.getRole()))
                .toList();
        return new ResponseEntity<>(new ApiResponseDTO<>(true, "Profiles list success", userResponseDTOList), HttpStatus.OK);
    }
}
