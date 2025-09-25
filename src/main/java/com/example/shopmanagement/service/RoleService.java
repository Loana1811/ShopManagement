package com.example.shopmanagement.service;

import com.example.shopmanagement.entity.Role;
import com.example.shopmanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> updateRole(Long id, Role roleDetails) {
        return roleRepository.findById(id).map(role -> {
            role.setName(roleDetails.getName());
            return roleRepository.save(role);
        });
    }

    public boolean deleteRole(Long id) {
        return roleRepository.findById(id).map(role -> {
            roleRepository.delete(role);
            return true;
        }).orElse(false);
    }
}
