package com.deekshith.userapi.service;

import com.deekshith.userapi.entity.Order;
import com.deekshith.userapi.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.deekshith.userapi.repository.OrderRepository;
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // ✅ Save Order
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // ✅ Get Orders (Pagination + Filtering)
    public Page<Order> getOrders(String productName, Pageable pageable) {
        return orderRepository
                .findByProductNameContaining(productName, pageable);
    }
}