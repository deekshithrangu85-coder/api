package com.deekshith.userapi.repository;

import com.deekshith.userapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByProductNameContaining(String productName, Pageable pageable);

}
