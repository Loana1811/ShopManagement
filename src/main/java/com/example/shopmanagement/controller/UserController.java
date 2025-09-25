package com.example.shopmanagement.controller;

import com.example.shopmanagement.entity.User;
import com.example.shopmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PutMapping("/{id}/status")
    public User updateStatus(@PathVariable Long id, @RequestBody Map<String,String> body){
        return userService.updateStatus(id, body.get("status"));
    }
}



