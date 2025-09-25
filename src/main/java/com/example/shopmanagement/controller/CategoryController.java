package com.example.shopmanagement.controller;

import com.example.shopmanagement.entity.Category;
import com.example.shopmanagement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id,
                                   @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    // ✅ endpoint PUT /api/categories/{id}/status
    @PutMapping("/{id}/status")
    public Category updateStatus(@PathVariable Long id,
                                 @RequestBody Map<String, String> body) {
        String status = body.get("status"); // ví dụ "ACTIVE" hoặc "INACTIVE"
        return categoryService.updateStatus(id, status);
    }
}
