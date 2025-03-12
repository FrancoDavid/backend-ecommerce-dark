package com.darkdev.ecommerce.ecommerce_backend.dto.category;

public class CategoryResponseDTO {
    private String name;

    public CategoryResponseDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
