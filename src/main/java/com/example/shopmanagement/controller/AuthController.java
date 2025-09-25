package com.example.shopmanagement.controller;

import com.example.shopmanagement.dto.DTO;
import com.example.shopmanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody DTO.AuthRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<DTO.AuthResponse> login(@RequestBody DTO.AuthRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new DTO.AuthResponse(token));
    }
}
