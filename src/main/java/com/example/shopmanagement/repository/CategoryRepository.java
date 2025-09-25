package com.example.shopmanagement.repository;

import com.example.shopmanagement.entity.Category; // ✅ Đúng
import com.example.shopmanagement.entity.Category; // ✅ Đúng

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

