package com.darkdev.ecommerce.ecommerce_backend.dto.order;

public class OrderResponseDTO {
    private Integer idOrder;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }
}
