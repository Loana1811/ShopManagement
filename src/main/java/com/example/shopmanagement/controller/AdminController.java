package com.example.shopmanagement.controller;

import com.example.shopmanagement.entity.Role;
import com.example.shopmanagement.entity.User;
import com.example.shopmanagement.repository.RoleRepository;
import com.example.shopmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminController {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    // Thêm quyền cho user
    @PostMapping("/{userId}/role/{roleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public User addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId){
        User user = userRepo.findById(userId).orElseThrow();
        Role role = roleRepo.findById(roleId).orElseThrow();
        user.getRoles().add(role);
        return userRepo.save(user);
    }

    // Xóa quyền của user
    @DeleteMapping("/{userId}/role/{roleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public User removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId){
        User user = userRepo.findById(userId).orElseThrow();
        Role role = roleRepo.findById(roleId).orElseThrow();
        user.getRoles().remove(role);
        return userRepo.save(user);
    }
}


