package com.example.shopmanagement.controller;

import com.example.shopmanagement.entity.User_role;
import com.example.shopmanagement.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<List<User_role>> getAllUserRoles() {
        return ResponseEntity.ok(userRoleService.getAllUserRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User_role> getUserRoleById(@PathVariable Long id) {
        Optional<User_role> userRole = userRoleService.getUserRoleById(id);
        return userRole.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User_role> createUserRole(@RequestBody User_role userRole) {
        return new ResponseEntity<>(userRoleService.createUserRole(userRole), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User_role> updateUserRole(@PathVariable Long id, @RequestBody User_role userRoleDetails) {
        Optional<User_role> updated = userRoleService.updateUserRole(id, userRoleDetails);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long id) {
        boolean deleted = userRoleService.deleteUserRole(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
