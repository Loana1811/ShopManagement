package com.example.shopmanagement.repository;

import com.example.shopmanagement.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    // ví dụ custom query:
    List<ShoppingCart> findByUserId(Long userId);
    @Transactional
    void deleteByUserId(Long userId);

}
