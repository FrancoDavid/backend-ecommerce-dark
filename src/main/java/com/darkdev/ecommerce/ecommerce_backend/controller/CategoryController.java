package com.darkdev.ecommerce.ecommerce_backend.controller;

import com.darkdev.ecommerce.ecommerce_backend.dto.category.CategoryDeleteResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.category.CategoryResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiErrorResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.exception.ValidationException;
import com.darkdev.ecommerce.ecommerce_backend.model.Category;
import com.darkdev.ecommerce.ecommerce_backend.service.CategoryService;
import com.darkdev.ecommerce.ecommerce_backend.utils.ExceptionUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Error validations", ExceptionUtils.getErrorsFromBinding(bindingResult));
        }

        Category categorySaved = categoryService.create(category);
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO(categorySaved.getName());

        return new ResponseEntity<>(new ApiResponseDTO<>(true, "Category created success", categoryResponseDTO), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Object> update(@Valid @RequestBody Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Error validations", false, null, null), HttpStatus.BAD_REQUEST);
        }

        try {
            Category categorySaved = categoryService.update(category);
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO(categorySaved.getName());

            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Category updated success", categoryResponseDTO), HttpStatus.OK);

        } catch (Exception e) {
            List<String> errors = Collections.singletonList(e.getMessage());
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Category updated failed", false, errors, null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Object> remove(@PathVariable Integer idCategory) {
        try {
            categoryService.remove(idCategory);
            CategoryDeleteResponseDTO categoryResponseDTO = new CategoryDeleteResponseDTO(idCategory);

            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Category removed success", categoryResponseDTO), HttpStatus.OK);
        } catch (Exception e) {
            List<String> errors = Collections.singletonList(e.getMessage());
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Category removed failed", false, errors, null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> categories() {
        try {
            List<Category> categoryList = categoryService.categories();
            List<CategoryResponseDTO> categoryResponseDTOList = categoryList.stream()
                    .map(category -> new CategoryResponseDTO(category.getName()))
                    .toList();

            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Categories found", categoryResponseDTOList), HttpStatus.OK);
        } catch (Exception e) {
            List<String> errors = Collections.singletonList(e.getMessage());

            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Categories not found", false, errors, null), HttpStatus.BAD_REQUEST);
        }

    }

}
