package com.example.shopmanagement.service;

import com.example.shopmanagement.entity.ShoppingCart;
import com.example.shopmanagement.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired

    private ShoppingCartRepository cartRepository;

    public List<ShoppingCart> getAllCarts() { return cartRepository.findAll(); }

    public Optional<ShoppingCart> getCartById(Long id) { return cartRepository.findById(id); }

    public ShoppingCart createCart(ShoppingCart cart) { return cartRepository.save(cart); }

    public Optional<ShoppingCart> updateCart(Long id, ShoppingCart cartDetails) {
        return cartRepository.findById(id).map(cart -> {
            cart.setUser(cartDetails.getUser());
            cart.setProduct(cartDetails.getProduct());
            cart.setQuantity(cartDetails.getQuantity());
            return cartRepository.save(cart);
        });
    }

    public boolean deleteCart(Long id) {
        return cartRepository.findById(id).map(cart -> {
            cartRepository.delete(cart);
            return true;
        }).orElse(false);
    }
    // ✅ thêm mới: lấy danh sách giỏ hàng theo user_id
    public List<ShoppingCart> getCartsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Transactional
    public void deleteCartsByUserId(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

}
