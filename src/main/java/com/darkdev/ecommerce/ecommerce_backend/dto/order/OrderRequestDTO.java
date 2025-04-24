package com.darkdev.ecommerce.ecommerce_backend.dto.order;

import java.util.List;

public class OrderRequestDTO {
    private String email;
    private List<OrderDetailsDTO> details;
    private Integer total;
    private Integer subtotal;
    private Integer delivery;

    public OrderRequestDTO(String email, List<OrderDetailsDTO> details, Integer total, Integer subtotal, Integer delivery) {
        this.email = email;
        this.details = details;
        this.total = total;
        this.delivery = delivery;
        this.subtotal = subtotal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrderDetailsDTO> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailsDTO> details) {
        this.details = details;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getDelivery() {
        return delivery;
    }

    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }
}
