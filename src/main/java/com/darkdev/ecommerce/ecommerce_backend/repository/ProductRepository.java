package com.darkdev.ecommerce.ecommerce_backend.repository;

import com.darkdev.ecommerce.ecommerce_backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByName(String name);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByCategory_name(String category, Pageable pageable);
}
