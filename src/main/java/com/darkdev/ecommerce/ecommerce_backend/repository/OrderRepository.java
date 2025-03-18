package com.darkdev.ecommerce.ecommerce_backend.repository;

import com.darkdev.ecommerce.ecommerce_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
