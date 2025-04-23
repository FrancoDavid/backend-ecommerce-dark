package com.darkdev.ecommerce.ecommerce_backend.dto.user;

public class UserResponseDTO {
    private String name;
    private String email;
    private String role;
    private String token;

    public UserResponseDTO(String name, String email, String role, String token) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
