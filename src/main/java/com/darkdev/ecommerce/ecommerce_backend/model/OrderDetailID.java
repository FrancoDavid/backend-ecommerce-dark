package com.darkdev.ecommerce.ecommerce_backend.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailID implements Serializable {

    private Integer idOrder;
    private Integer idProduct;
    private Integer idCategory;
    private Integer idUser;

    public OrderDetailID() {
    }

    public OrderDetailID(Integer idOrder, Integer idProduct, Integer idCategory, Integer idUser) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.idCategory = idCategory;
        this.idUser = idUser;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailID that = (OrderDetailID) o;
        return Objects.equals(idOrder, that.idOrder) &&
                Objects.equals(idProduct, that.idProduct) &&
                Objects.equals(idCategory, that.idCategory) &&
                Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idProduct, idCategory, idUser);
    }
}
