package com.example.shopmanagement.service;

import com.example.shopmanagement.entity.Order;
import com.example.shopmanagement.entity.OrderDetail;
import com.example.shopmanagement.entity.User;
import com.example.shopmanagement.repository.OrderRepository;
import com.example.shopmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository; // thêm để tìm user theo id

    // Lấy tất cả đơn hàng
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Lấy đơn hàng theo ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Tạo mới đơn hàng
    public Order createOrder(Order order) {
        // Trạng thái mặc định
        if (order.getStatus() == null) {
            order.setStatus("PENDING");
        }

        // Nếu User null → báo lỗi
        if (order.getUser() == null || order.getUser().getId() == null) {
            throw new RuntimeException("User must not be null");
        } else {
            // Lấy User từ DB rồi set vào Order
            User user = userRepository.findById(order.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            order.setUser(user);
        }

        // Set order cho từng detail trước khi save
        if (order.getDetails() != null) {
            for (OrderDetail detail : order.getDetails()) {
                detail.setOrder(order);
            }
        }

        return orderRepository.save(order);
    }

    // Cập nhật đơn hàng
    public Optional<Order> updateOrder(Long id, Order orderDetails) {
        return orderRepository.findById(id).map(order -> {
            order.setReceiveDate(orderDetails.getReceiveDate());
            order.setStatus(orderDetails.getStatus());

            // Update user
            if (orderDetails.getUser() != null && orderDetails.getUser().getId() != null) {
                User user = userRepository.findById(orderDetails.getUser().getId())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                order.setUser(user);
            }

            // Xóa chi tiết cũ và thêm mới
            order.getDetails().clear();
            if (orderDetails.getDetails() != null) {
                for (OrderDetail detail : orderDetails.getDetails()) {
                    detail.setOrder(order); // quan trọng
                    order.getDetails().add(detail);
                }
            }

            return orderRepository.save(order);
        });
    }

    // Lấy đơn hàng theo userId
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // Xóa đơn hàng
    public boolean deleteOrder(Long id) {
        return orderRepository.findById(id).map(order -> {
            orderRepository.delete(order);
            return true;
        }).orElse(false);
    }
}
