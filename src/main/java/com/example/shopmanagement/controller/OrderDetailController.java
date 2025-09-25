package com.example.shopmanagement.controller;

import com.example.shopmanagement.entity.OrderDetail;
import com.example.shopmanagement.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        return ResponseEntity.ok(orderDetailService.getAllOrderDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable Long id) {
        Optional<OrderDetail> od = orderDetailService.getOrderDetailById(id);
        return od.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail detail) {
        return new ResponseEntity<>(orderDetailService.createOrderDetail(detail), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetail detail) {
        Optional<OrderDetail> updated = orderDetailService.updateOrderDetail(id, detail);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id) {
        boolean deleted = orderDetailService.deleteOrderDetail(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
