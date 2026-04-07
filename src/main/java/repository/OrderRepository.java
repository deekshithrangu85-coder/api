package com.deekshith.userapi.repository;

import com.deekshith.userapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 🔹 Pagination
    Page<Order> findByProductNameContaining(String productName, Pageable pageable);

    // 🔹 SUM
    @Query("SELECT SUM(o.price) FROM Order o WHERE o.user.id = :userId")
    Double getTotalAmountForUser(@Param("userId") Long userId);

    // 🔹 COUNT
    @Query("SELECT COUNT(o) FROM Order o WHERE o.user.id = :userId")
    Long countOrdersByUser(@Param("userId") Long userId);
}