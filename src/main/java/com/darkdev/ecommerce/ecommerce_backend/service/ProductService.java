package com.darkdev.ecommerce.ecommerce_backend.service;

import com.darkdev.ecommerce.ecommerce_backend.model.Category;
import com.darkdev.ecommerce.ecommerce_backend.model.Product;
import com.darkdev.ecommerce.ecommerce_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product, Category category) throws Exception {
        product.setCategory(category);
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new Exception("Create product failed");
        }
    };
    public Product update(Product product, Category category) throws Exception {
        product.setCategory(category);
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new Exception("Create product failed");
        }
    };
    public void remove(Integer idProduct) throws Exception {
        try {
            productRepository.deleteById(idProduct);
        } catch (Exception e) {
            throw new Exception("Remove product failed");
        }
    };
    public Product product(Integer idProduct) throws Exception {
        return productRepository.findById(idProduct)
                .orElseThrow(() -> new Exception("Product not found"));
    };
    public List<Product> products() throws Exception {
        try {
            return productRepository.findAll();

        } catch (Exception e) {
            throw new Exception("Categories not found");
        }
    };
}
