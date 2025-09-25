package com.example.shopmanagement.repository;

import com.example.shopmanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Thêm dòng này để tìm role theo tên
    Optional<Role> findByName(String name);
}
