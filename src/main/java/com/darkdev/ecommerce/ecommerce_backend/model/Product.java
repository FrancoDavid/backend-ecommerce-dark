package com.darkdev.ecommerce.ecommerce_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idproduct")
    private Integer idProduct;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    private String description;

    private Integer price;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_idcategory", nullable = false)
    private Category category;

    public Product() {}

    public Product(Integer idProduct, String description, String name, Integer price, Integer stock, Category category) {
        this.idProduct = idProduct;
        this.description = description;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
