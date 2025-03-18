package com.darkdev.ecommerce.ecommerce_backend.service;

import com.darkdev.ecommerce.ecommerce_backend.model.Order;
import com.darkdev.ecommerce.ecommerce_backend.model.User;
import com.darkdev.ecommerce.ecommerce_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order create(User user, Integer total) throws RuntimeException {
           try {
               Order order = new Order(null, total, user);
               return orderRepository.save(order);

           } catch (RuntimeException e) {
               throw new RuntimeException("Create order failed: " + e.getMessage());
           }
    };
}
