package com.darkdev.ecommerce.ecommerce_backend.service;

import com.darkdev.ecommerce.ecommerce_backend.model.Category;
import com.darkdev.ecommerce.ecommerce_backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(Category category) {
        try {
            return categoryRepository.save(category);

        } catch (Exception  e) {
            throw new RuntimeException("Create category failed: "+ e.getMessage());
        }
    };

    public Category update(Category category) {
        try {
            return categoryRepository.save(category);

        } catch (Exception e) {
            throw new RuntimeException("Updated category failed: "+ e.getMessage());
        }
    };

    public void remove(Integer idCategory) {
        try {
            categoryRepository.deleteById(idCategory);

        } catch (Exception e) {
            throw new RuntimeException("Removed category failed: "+e.getMessage());
        }
    };

    public List<Category> categories() {
        try {
            return categoryRepository.findAll();

        } catch (Exception e) {
            throw new RuntimeException("Categories not found:" +e.getMessage());
        }
    };

    public Category category(Integer idCategory) {
         return categoryRepository.findById(idCategory)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
