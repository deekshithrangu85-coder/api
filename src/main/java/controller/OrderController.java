package com.deekshith.userapi.controller;
import com.deekshith.userapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // 🔹 Get Total Order Price
    @GetMapping("/total/{userId}")
    public ResponseEntity<Double> getTotalOrderAmount(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getTotalOrderAmount(userId));
    }

    // 🔹 Get Order Count
    @GetMapping("/count/{userId}")
    public ResponseEntity<Long> getOrderCount(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrderCount(userId));
    }
}