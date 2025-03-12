package com.darkdev.ecommerce.ecommerce_backend.controller;

import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiErrorResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.product.ProductDeleteResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.product.ProductResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.model.Category;
import com.darkdev.ecommerce.ecommerce_backend.model.Product;
import com.darkdev.ecommerce.ecommerce_backend.service.CategoryService;
import com.darkdev.ecommerce.ecommerce_backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody Product product, Integer idCategory, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Error validators", false, null, null), HttpStatus.BAD_REQUEST);
        }

        try {
            Category category = categoryService.category(idCategory);
            Product productSaved = productService.create(product, category);
            ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                    productSaved.getName(),
                    productSaved.getDescription(),
                    productSaved.getPrice(),
                    productSaved.getStock(),
                    category.getName()
            );

            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Product created", productResponseDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Product create failed", false, null, null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<Object> update(@Valid @RequestBody Product product, Integer idCategory, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Error validators", false, null, null), HttpStatus.BAD_REQUEST);
        }

        try {
            Category category = categoryService.category(idCategory);
            Product productSaved = productService.update(product, category);
            ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                    productSaved.getName(),
                    productSaved.getDescription(),
                    productSaved.getPrice(),
                    productSaved.getStock(),
                    category.getName()
            );

            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Product updated", productResponseDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Product update failed", false, null, null), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping()
    public ResponseEntity<Object> remove(@PathVariable Integer idProduct) {
        try {
            productService.remove(idProduct);
            ProductDeleteResponseDTO productDeleteResponseDTO = new ProductDeleteResponseDTO(idProduct);
            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Product removed", productDeleteResponseDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Product removed failed", false, null, null), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping()
    public ResponseEntity<Object> product(@PathVariable Integer idProduct) {
        try {
            Product product = productService.product(idProduct);
            Category category = product.getCategory();

            ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock(),
                    category.getName()
            );

            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Product found", productResponseDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Product not found", false, null, null), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<Object> products() {
        try {
            List<Product> productList = productService.products();
            List<ProductResponseDTO> productResponseDTOList = productList.stream()
                    .map(product -> {
                      Category category = product.getCategory();
                      return new ProductResponseDTO(
                              product.getName(),
                              product.getDescription(),
                              product.getPrice(),
                              product.getStock(),
                              category.getName()
                      );
                    })
                    .toList();
            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Products found", productResponseDTOList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Products not found", false, null, null), HttpStatus.NOT_FOUND);
        }
    }
}
