package com.darkdev.ecommerce.ecommerce_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailID id;

    @ManyToOne
    @MapsId("idOrder")
    @JoinColumn(name = "order_idorder")
    private Order idOrder;

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "order_user_iduser")
    private User idUser;

    @ManyToOne
    @MapsId("idProduct")
    @JoinColumn(name = "product_idproduct")
    private Product idProduct;

    @ManyToOne
    @MapsId("idCategory")
    @JoinColumn(name = "product_category_idcategory")
    private Category idCategory;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetailID id, Order idOrder, User idUser, Product idProduct, Category idCategory, Integer quantity) {
        this.id = id;
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.idCategory = idCategory;
        this.quantity = quantity;
    }

    public OrderDetailID getId() {
        return id;
    }

    public void setId(OrderDetailID id) {
        this.id = id;
    }

    public Order getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Order idOrder) {
        this.idOrder = idOrder;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Category idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
