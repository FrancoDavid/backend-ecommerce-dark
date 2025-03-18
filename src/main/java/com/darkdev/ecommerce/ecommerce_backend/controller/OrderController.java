package com.darkdev.ecommerce.ecommerce_backend.controller;

import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiErrorResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiResponseDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.order.OrderDetailsDTO;
import com.darkdev.ecommerce.ecommerce_backend.dto.order.OrderHistorialDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/{email}")
    public ResponseEntity<Object> historialByUser(@PathVariable String email) {
        try {
            User user = userService.searchByEmail(email);
            List<Order> orderList = orderService.orderList(user);

            List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<OrderDetailsDTO>();
            List<OrderHistorialDTO> orderHistorialDTOS = new ArrayList<OrderHistorialDTO>();

            for (Order item : orderList) {
                List<OrderDetail> orderDetailList = orderDetailService.detailList(item);
                for (OrderDetail orderDetail : orderDetailList) {
                    try {
                        Product productDetail = productService.product(orderDetail.getIdProduct().getIdProduct());
                        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                                productDetail.getIdProduct(),
                                productDetail.getName(),
                                productDetail.getPrice(),
                                orderDetail.getQuantity()
                        );

                        orderDetailsDTOS.add(orderDetailsDTO);
                    } catch (Exception e) {
                        return new ResponseEntity<>(new ApiErrorResponseDTO<>("Product not found: "+ e.getMessage(), false, null, null), HttpStatus.NOT_FOUND);
                    }
                }
                OrderHistorialDTO orderHistorialDTO = new OrderHistorialDTO(user.getEmail(), item.getIdOrder(), orderDetailsDTOS, item.getTotal());
                orderHistorialDTOS.add(orderHistorialDTO);
            }

            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Order founds", orderHistorialDTOS), HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Orders not found: "+ e.getMessage(), false, null, null), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> historialOrders() {
        try {
            List<Order> orderList = orderService.orderAllList();
            List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<OrderDetailsDTO>();
            List<OrderHistorialDTO> orderHistorialDTOS = new ArrayList<OrderHistorialDTO>();

            for (Order item : orderList) {
                try {
                    User user = userService.detail(item.getIdUser().getIdUser());
                    List<OrderDetail> orderDetailList = orderDetailService.detailList(item);

                    for (OrderDetail orderDetail : orderDetailList) {
                        try {
                            Product productDetail = productService.product(orderDetail.getIdProduct().getIdProduct());
                            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                                    productDetail.getIdProduct(),
                                    productDetail.getName(),
                                    productDetail.getPrice(),
                                    orderDetail.getQuantity()
                            );

                            orderDetailsDTOS.add(orderDetailsDTO);
                        } catch (Exception e) {
                            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Product not found: "+ e.getMessage(), false, null, null), HttpStatus.NOT_FOUND);
                        }
                    }

                    OrderHistorialDTO orderHistorialDTO = new OrderHistorialDTO(user.getEmail(), item.getIdOrder(), orderDetailsDTOS, item.getTotal());
                    orderHistorialDTOS.add(orderHistorialDTO);
                } catch (Exception e) {
                    return new ResponseEntity<>(new ApiErrorResponseDTO<>("User not found: "+ e.getMessage(), false, null, null), HttpStatus.NOT_FOUND);
                }

            }

            return new ResponseEntity<>(new ApiResponseDTO<>(true, "Order founds", orderHistorialDTOS), HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiErrorResponseDTO<>("Orders not found: "+ e.getMessage(), false, null, null), HttpStatus.NOT_FOUND);
        }
    }
}
