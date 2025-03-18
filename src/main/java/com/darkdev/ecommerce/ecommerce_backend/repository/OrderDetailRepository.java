package com.darkdev.ecommerce.ecommerce_backend.repository;

import com.darkdev.ecommerce.ecommerce_backend.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
