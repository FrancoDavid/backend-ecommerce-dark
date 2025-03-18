package com.darkdev.ecommerce.ecommerce_backend.controller;

import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiErrorResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.order.OrderDetailsDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.order.OrderRequestDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.order.OrderResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.model.*;
import com.darkdev.ecommerce.ecommerce_backend.service.OrderDetailService;
import com.darkdev.ecommerce.ecommerce_backend.service.OrderService;
import com.darkdev.ecommerce.ecommerce_backend.service.ProductService;
import com.darkdev.ecommerce.ecommerce_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody OrderRequestDTO orderRequestDTO, BindingResult bindingResult) {

        try {
            User user = userService.searchByEmail(orderRequestDTO.getEmail());
            Order order = orderService.create(user, orderRequestDTO.getTotal());

            for (OrderDetailsDTO items : orderRequestDTO.getDetails()) {
                try {
                    Product product = productService.product(items.getIdProduct());
                    orderDetailService.create(order, user, product, product.getCategory(), items.getQuantity());
                } catch (Exception e) {
                    return new ResponseEntity<>(new ApiErrorResponseDTO<>("Cannot generate order because not found products", false, null , null), HttpStatus.BAD_REQUEST);
                }
            }

            OrderResponseDTO orderResponseDTO = new OrderResponseDTO(order.getIdOrder());

            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Order generate succesfully", orderResponseDTO), HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Cannot generate order", false, null , null), HttpStatus.BAD_REQUEST);
        }

    };
}
