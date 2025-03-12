package com.darkdev.ecommerce.ecommerce_backend.dto.product;

public class ProductDeleteResponseDTO {
    private Integer idProduct;

    public ProductDeleteResponseDTO(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }
}
