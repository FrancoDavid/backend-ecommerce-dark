package com.darkdev.ecommerce.ecommerce_backend.dto.user;

public class UserResponseDTO {
    private String name;
    private String email;
    private String role;

    public UserResponseDTO(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
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
}
