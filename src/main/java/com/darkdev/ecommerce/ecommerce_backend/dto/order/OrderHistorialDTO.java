package com.darkdev.ecommerce.ecommerce_backend.dto.order;

import java.util.List;

public class OrderHistorialDTO {
    private String email;
    private Integer idOrder;
    private Integer total;
    private List<OrderDetailsDTO> details;

    public OrderHistorialDTO() {
    }

    public OrderHistorialDTO(String email, Integer idOrder, List<OrderDetailsDTO> details, Integer total) {
        this.email = email;
        this.idOrder = idOrder;
        this.details = details;
        this.total = total;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
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
}
