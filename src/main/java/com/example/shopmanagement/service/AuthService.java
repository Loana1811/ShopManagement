package com.example.shopmanagement.service;

import com.example.shopmanagement.dto.DTO;
import com.example.shopmanagement.entity.Role;
import com.example.shopmanagement.entity.User;
import com.example.shopmanagement.jwt.JwtUtil;

import com.example.shopmanagement.repository.RoleRepository;
import com.example.shopmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

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
        // nếu user đã tồn tại → trả 409 Conflict thay vì 403
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // set role
        Set<Role> roles = request.getRoleNames().stream()
                .map(name -> roleRepository.findByName(name)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Role not found: " + name)))
                .collect(Collectors.toSet());

        // thay roleName bằng id
//        Set<Role> roles = request.getRoleIds().stream()
//                .map(id -> roleRepository.findById(id)
//                        .orElseThrow(() -> new ResponseStatusException(
//                                HttpStatus.BAD_REQUEST, "Role id không tồn tại: " + id)))
//                .collect(Collectors.toSet());
        user.setRoles(roles);  // <-- gán vào user
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
