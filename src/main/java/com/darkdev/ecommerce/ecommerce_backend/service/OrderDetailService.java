package com.darkdev.ecommerce.ecommerce_backend.service;

import com.darkdev.ecommerce.ecommerce_backend.model.*;
import com.darkdev.ecommerce.ecommerce_backend.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetail create(Order order, User user, Product product, Category category, Integer quantity) {
        try {
            OrderDetailID orderDetailID = new OrderDetailID(order.getIdOrder(), product.getIdProduct(), category.getIdCategory(), user.getIdUser());
            OrderDetail orderDetail = new OrderDetail(orderDetailID, order, user, product, category, quantity);

            return orderDetailRepository.save(orderDetail);
        } catch (Exception e) {
            throw new RuntimeException("Cannot be created order details: "+ e.getMessage());
        }
    }

    public List<OrderDetail> detailList(Order order) {
        try {
            return orderDetailRepository.findByIdOrder_idOrder(order.getIdOrder());
        } catch (Exception e) {
            throw new RuntimeException("Order details not found: "+ e.getMessage());
        }
    }
}
