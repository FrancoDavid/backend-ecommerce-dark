package com.darkdev.ecommerce.ecommerce_backend.dto.category;

public class CategoryDeleteResponseDTO {
    private Integer idCategory;

    public CategoryDeleteResponseDTO(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }
}
