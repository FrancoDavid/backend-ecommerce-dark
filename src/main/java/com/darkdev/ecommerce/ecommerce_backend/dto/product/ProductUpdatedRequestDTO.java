package com.darkdev.ecommerce.ecommerce_backend.dto.product;

public class ProductUpdatedRequestDTO {
    private String name;
    private Integer idProduct;
    private String description;
    private Integer price;
    private Integer stock;
    private Integer idCategory;
    private String url;

    public ProductUpdatedRequestDTO(Integer idProduct,String name, String description, Integer price, Integer stock, Integer idCategory, String url) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.idCategory = idCategory;
        this.url = url;
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

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
