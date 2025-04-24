package com.darkdev.ecommerce.ecommerce_backend.service;

import com.darkdev.ecommerce.ecommerce_backend.model.Order;
import com.darkdev.ecommerce.ecommerce_backend.model.User;
import com.darkdev.ecommerce.ecommerce_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order create(User user, Integer total, Integer subtotal, Integer delivery) {
           try {
               Order order = new Order(null, total, subtotal, delivery, user);
               return orderRepository.save(order);

           } catch (Exception e) {
               throw new RuntimeException("Create order failed: " + e.getMessage());
           }
    };

    public List<Order> orderList(User user) {
        try {
            return orderRepository.findByIdUser_IdUser(user.getIdUser());
        } catch (Exception e) {
            throw new RuntimeException("Orders not found" + e.getMessage());
        }
    }

    public List<Order> orderAllList() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Orders not found" + e.getMessage());
        }
    }
}
