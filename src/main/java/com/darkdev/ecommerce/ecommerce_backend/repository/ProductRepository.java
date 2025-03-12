package com.darkdev.ecommerce.ecommerce_backend.repository;

import com.darkdev.ecommerce.ecommerce_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
