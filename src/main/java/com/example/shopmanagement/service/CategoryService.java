package com.example.shopmanagement.service;

import com.example.shopmanagement.entity.Category;
import com.example.shopmanagement.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public Category update(Long id, Category newCategory) {
        Category c = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        c.setName(newCategory.getName());       // ✅ vì entity giờ là 'name'
        c.setStatus(newCategory.getStatus());   // status là String
        return categoryRepo.save(c);
    }

    // ✅ phương thức cập nhật status riêng
    public Category updateStatus(Long id, String status) {
        Category c = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        c.setStatus(status);
        return categoryRepo.save(c);
    }
}
