package com.example.shopmanagement.service;

import com.example.shopmanagement.entity.Product;
import com.example.shopmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        // Default status nếu chưa set
        if(product.getStatus() == null) product.setStatus("ACTIVE");
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setStock(productDetails.getStock());
            product.setCategory(productDetails.getCategory());
            product.setStatus(productDetails.getStatus());
            return productRepository.save(product);
        });
    }

    public Product updateStatus(Long id, String status) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStatus(status);
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {
        return productRepository.findById(id).map(product -> {
            productRepository.delete(product);
            return true;
        }).orElse(false);
    }
    // 🔹 Phân trang, sắp xếp, tìm kiếm theo name và category
    public Page<Product> getProducts(String name, Long categoryId, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if ((name == null || name.isEmpty()) && categoryId == null) {
            return productRepository.findAll(pageable);
        } else if (categoryId == null) {
            return productRepository.findByNameContainingIgnoreCase(name, pageable);
        } else if (name == null || name.isEmpty()) {
            return productRepository.findByCategoryId(categoryId, pageable);
        } else {
            return productRepository.findByNameContainingIgnoreCaseAndCategoryId(name, categoryId, pageable);
        }
    }

}
