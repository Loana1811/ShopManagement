package com.example.shopmanagement.controller;

import com.example.shopmanagement.entity.ShoppingCart;
import com.example.shopmanagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @GetMapping
    public ResponseEntity<List<ShoppingCart>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getCartById(@PathVariable Long id) {
        Optional<ShoppingCart> cart = cartService.getCartById(id);
        return cart.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> createCart(@RequestBody ShoppingCart cart) {
        return new ResponseEntity<>(cartService.createCart(cart), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCart> updateCart(@PathVariable Long id, @RequestBody ShoppingCart cartDetails) {
        Optional<ShoppingCart> updated = cartService.updateCart(id, cartDetails);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        boolean deleted = cartService.deleteCart(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ShoppingCart>> getCartsByUserId(@PathVariable Long userId) {
        List<ShoppingCart> carts = cartService.getCartsByUserId(userId);
        return ResponseEntity.ok(carts);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteCartsByUserId(@PathVariable Long userId) {
        cartService.deleteCartsByUserId(userId);
        return ResponseEntity.noContent().build();
    }

}
