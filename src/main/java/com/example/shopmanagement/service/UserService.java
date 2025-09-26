package com.example.shopmanagement.service;

import com.example.shopmanagement.entity.User;
import com.example.shopmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public User updateStatus(Long id, String status){
        User u = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        u.setStatus(status);
        return userRepo.save(u);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Xóa quan hệ với Role trước
        user.getRoles().clear();

        // Xóa user
        userRepo.delete(user);
    }
}



