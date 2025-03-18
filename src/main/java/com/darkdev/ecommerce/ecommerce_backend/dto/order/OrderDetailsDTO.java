package com.darkdev.ecommerce.ecommerce_backend.dto.order;

public class OrderDetailsDTO {
    private Integer idProduct;
    private String name;
    private Integer unitPrice;
    private Integer quantity;

    public OrderDetailsDTO(Integer idProduct, String name, Integer unitPrice, Integer quantity) {
        this.idProduct = idProduct;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
