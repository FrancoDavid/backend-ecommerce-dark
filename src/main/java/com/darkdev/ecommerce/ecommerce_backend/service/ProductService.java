package com.darkdev.ecommerce.ecommerce_backend.service;

import com.darkdev.ecommerce.ecommerce_backend.exception.BadRequestException;
import com.darkdev.ecommerce.ecommerce_backend.model.Category;
import com.darkdev.ecommerce.ecommerce_backend.model.Product;
import com.darkdev.ecommerce.ecommerce_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product, Category category) {
        product.setCategory(category);
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Create product failed");
        }
    };

    public Product update(Product product, Category category) {
        product.setCategory(category);
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Create product failed");
        }
    };

    public void remove(Integer idProduct) {
        try {
            productRepository.deleteById(idProduct);
        } catch (Exception e) {
            throw new RuntimeException("Remove product failed");
        }
    };

    public Product product(Integer idProduct) {
        return productRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    };

    public Page<Product> products(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("idProduct").ascending());
            return productRepository.findAll(pageable);

        } catch (Exception e) {
            throw new RuntimeException("Products not found");
        }
    };

    public List<Product> searchByName(String name) {
        try {
            return productRepository.findByName(name);
        } catch (Exception e) {
            throw new RuntimeException("Product not found: " + e.getMessage());
        }
    }

    public List<Product> productsLasts() {
        try {
            return productRepository
                    .findAll(Sort.by(Sort.Direction.DESC, "idProduct"));

        } catch (Exception e) {
            throw new RuntimeException("Products not found");
        }
    };

    public void discountStock(Product product, Integer quantity) {
        try {
            int newStock = product.getStock() - quantity;

            if (newStock < 0) {
                throw  new BadRequestException("Stock empty", "This product cannot buy it!");
            }

            product.setStock(newStock);
            productRepository.save(product);

        } catch (Exception e) {
            throw new RuntimeException("Product not apply descount stock");
        }
    }

    public Page<Product> productsByCategory(String category, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("idProduct").ascending());
            return productRepository.findByCategory_name(category, pageable);

        } catch (Exception e) {
            throw new RuntimeException("Products not found");
        }
    };


}
