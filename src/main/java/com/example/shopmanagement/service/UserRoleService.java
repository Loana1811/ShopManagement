package com.example.shopmanagement.service;

import com.example.shopmanagement.entity.User_role;

import com.example.shopmanagement.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<User_role> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    public Optional<User_role> getUserRoleById(Long id) {
        return userRoleRepository.findById(id);
    }

    public User_role createUserRole(User_role userRole) {
        return userRoleRepository.save(userRole);
    }

    public Optional<User_role> updateUserRole(Long id, User_role userRoleDetails) {
        return userRoleRepository.findById(id).map(userRole -> {
            userRole.setUser(userRoleDetails.getUser());
            userRole.setRole(userRoleDetails.getRole());
            return userRoleRepository.save(userRole);
        });
    }

    public boolean deleteUserRole(Long id) {
        return userRoleRepository.findById(id).map(userRole -> {
            userRoleRepository.delete(userRole);
            return true;
        }).orElse(false);
    }
}
