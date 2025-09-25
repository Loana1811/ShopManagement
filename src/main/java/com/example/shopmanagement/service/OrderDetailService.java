package com.example.shopmanagement.service;

import com.example.shopmanagement.entity.OrderDetail;
import com.example.shopmanagement.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAllOrderDetails() { return orderDetailRepository.findAll(); }

    public Optional<OrderDetail> getOrderDetailById(Long id) { return orderDetailRepository.findById(id); }

    public OrderDetail createOrderDetail(OrderDetail detail) { return orderDetailRepository.save(detail); }

    public Optional<OrderDetail> updateOrderDetail(Long id, OrderDetail detail) {
        return orderDetailRepository.findById(id).map(od -> {
            od.setOrder(detail.getOrder());
            od.setProduct(detail.getProduct());
            od.setQuantity(detail.getQuantity());
            od.setUnitPrice(detail.getUnitPrice());
            return orderDetailRepository.save(od);
        });
    }

    public boolean deleteOrderDetail(Long id) {
        return orderDetailRepository.findById(id).map(od -> {
            orderDetailRepository.delete(od);
            return true;
        }).orElse(false);
    }
}
