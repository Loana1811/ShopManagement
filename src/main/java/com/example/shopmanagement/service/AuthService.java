package com.example.shopmanagement.service;

import com.example.shopmanagement.dto.DTO;
import com.example.shopmanagement.entity.Role;
import com.example.shopmanagement.entity.User;
import com.example.shopmanagement.jwt.JwtUtil;

import com.example.shopmanagement.repository.RoleRepository;
import com.example.shopmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RoleRepository roleRepository;

    public void register(DTO.AuthRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent())
            throw new RuntimeException("User already exists");

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // set role
        Role role = (Role) roleRepository.findByName(request.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Set.of(role));

        userRepository.save(user);
    }

    public String login(DTO.AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        return jwtUtil.generateToken(user.getEmail());  // có thể dùng email làm subject
    }

}
