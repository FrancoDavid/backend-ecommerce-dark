package com.darkdev.ecommerce.ecommerce_backend.controller;

import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.product.*;
import com.darkdev.ecommerce.ecommerce_backend.exception.NotFoundException;
import com.darkdev.ecommerce.ecommerce_backend.exception.ValidationException;
import com.darkdev.ecommerce.ecommerce_backend.model.Category;
import com.darkdev.ecommerce.ecommerce_backend.model.Product;
import com.darkdev.ecommerce.ecommerce_backend.service.CategoryService;
import com.darkdev.ecommerce.ecommerce_backend.service.ProductService;
import com.darkdev.ecommerce.ecommerce_backend.utils.ExceptionUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Object> create(@Valid @RequestBody ProductRequestDTO productRequestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationException("Error validations", ExceptionUtils.getErrorsFromBinding(bindingResult));
        }

        Category category = categoryService.category(productRequestDTO.getIdCategory());
        Product product = new Product(
            null,
                productRequestDTO.getDescription(),
                productRequestDTO.getName(),
                productRequestDTO.getPrice(),
                productRequestDTO.getStock(),
                productRequestDTO.getUrl(),
                category
        );
        Product productSaved = productService.create(product, category);
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                productSaved.getName(),
                productSaved.getDescription(),
                productSaved.getPrice(),
                productSaved.getStock(),
                category.getName(),
                productSaved.getUrl(),
                productSaved.getIdProduct()
        );

        return new ResponseEntity<>(new ApiResponseDTO<>(true, "Product created", productResponseDTO), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Object> update(@Valid @RequestBody ProductUpdatedRequestDTO productUpdatedRequestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationException("Error validations", ExceptionUtils.getErrorsFromBinding(bindingResult));
        }

        Category category = categoryService.category(productUpdatedRequestDTO.getIdCategory());
        Product product = new Product(
                productUpdatedRequestDTO.getIdProduct(),
                productUpdatedRequestDTO.getDescription(),
                productUpdatedRequestDTO.getName(),
                productUpdatedRequestDTO.getPrice(),
                productUpdatedRequestDTO.getStock(),
                productUpdatedRequestDTO.getUrl(),
                category
        );
        Product productSaved = productService.update(product, category);
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                productSaved.getName(),
                productSaved.getDescription(),
                productSaved.getPrice(),
                productSaved.getStock(),
                category.getName(),
                productSaved.getUrl(),
                productSaved.getIdProduct()
        );

        return new ResponseEntity<>(new ApiResponseDTO<>(true, "Product updated", productResponseDTO), HttpStatus.OK);

    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Object> remove(@PathVariable Integer idProduct) {
        productService.remove(idProduct);
        ProductDeleteResponseDTO productDeleteResponseDTO = new ProductDeleteResponseDTO(idProduct);

        return new ResponseEntity<>(new ApiResponseDTO<>(true, "Product removed", productDeleteResponseDTO), HttpStatus.OK);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Object> product(@PathVariable Integer idProduct) {
            Product product = productService.product(idProduct);
            Category category = product.getCategory();

            ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock(),
                    category.getName(),
                    product.getUrl(),
                    product.getIdProduct()
            );

            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Product found", productResponseDTO), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> products(
            @RequestParam(required = false) String categoryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
            Page<Product> productPage;

            if (categoryName != null && !categoryName.isEmpty()) {
                productPage = productService.productsByCategory(categoryName, page, size);
            } else {
                productPage = productService.products(page, size);
            }

            List<ProductResponseDTO> productResponseDTOList = productPage
                    .map(product -> {
                      Category category = product.getCategory();
                      return new ProductResponseDTO(
                              product.getName(),
                              product.getDescription(),
                              product.getPrice(),
                              product.getStock(),
                              category.getName(),
                              product.getUrl(),
                              product.getIdProduct()
                      );
                    })
                    .toList();

            ProductResponsePageableDTO productResponsePageableDTO = new ProductResponsePageableDTO(
                    page,
                    productPage.getSize(),
                    productPage.getNumberOfElements(),
                    productPage.getTotalElements(),
                    productPage.getTotalPages(),
                    productResponseDTOList
            );

            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Products found", productResponsePageableDTO), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Object> searchProduct(@PathVariable String name) {
            List<Product> productList = productService.searchByName(name);
            List<ProductResponseDTO> productResponseDTOList = productList.stream()
                    .map(product -> {
                        Category category = product.getCategory();
                        return new ProductResponseDTO(
                                product.getName(),
                                product.getDescription(),
                                product.getPrice(),
                                product.getStock(),
                                category.getName(),
                                product.getUrl(),
                                product.getIdProduct()
                        );
                    })
                    .toList();


            if (productResponseDTOList.isEmpty()) {
                throw new NotFoundException("Products not found", "Products not found");
            } else {
                return new ResponseEntity<>(new ApiResponseDTO<>(true, "Products found", productResponseDTOList), HttpStatus.OK);
            }
    }

    @GetMapping("/lasts")
    public ResponseEntity<Object> productsLasts() {
            List<Product> productList = productService.productsLasts();
            List<ProductResponseDTO> productResponseDTOList = productList.stream()
                    .map(product -> {
                        Category category = product.getCategory();
                        return new ProductResponseDTO(
                                product.getName(),
                                product.getDescription(),
                                product.getPrice(),
                                product.getStock(),
                                category.getName(),
                                product.getUrl(),
                                product.getIdProduct()
                        );
                    })
                    .toList();
            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Products found", productResponseDTOList), HttpStatus.OK);
    }

}
