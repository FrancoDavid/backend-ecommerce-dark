package com.darkdev.ecommerce.ecommerce_backend.service;

import com.darkdev.ecommerce.ecommerce_backend.model.Category;
import com.darkdev.ecommerce.ecommerce_backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(Category category) throws Exception {
        try {
            return categoryRepository.save(category);

        } catch (DataIntegrityViolationException  e) {
            throw new Exception("Create category failed");
        }
    };

    public Category update(Category category) throws Exception {
        try {
            return categoryRepository.save(category);

        } catch (Exception e) {
            throw new Exception("Updated category failed");
        }
    };

    public void remove(Integer idCategory) throws Exception {
        try {
            categoryRepository.deleteById(idCategory);

        } catch (Exception e) {
            throw new Exception("Removed category failed");
        }
    };

    public List<Category> categories() throws Exception {
        try {
            return categoryRepository.findAll();

        } catch (Exception e) {
            throw new Exception("Categories not found");
        }
    };
}
