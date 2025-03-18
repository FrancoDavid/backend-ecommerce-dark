package com.darkdev.ecommerce.ecommerce_backend.repository;

import com.darkdev.ecommerce.ecommerce_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
