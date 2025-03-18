package com.darkdev.ecommerce.ecommerce_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorder")
    private Integer idOrder;

    @NotNull(message = "Total cannot be null")
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "user_iduser", nullable = false)
    private User idUser;

    public Order() {
    }

    public Order(Integer idOrder, Integer total, User idUser) {
        this.idOrder = idOrder;
        this.total = total;
        this.idUser = idUser;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
