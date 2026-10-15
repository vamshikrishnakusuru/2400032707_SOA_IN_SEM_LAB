package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository repository;

    public AuthController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return repository.findByUsername(request.getUsername())
            .filter(user ->
                user.getPassword().equals(request.getPassword()))
            .map(user -> "Login Successful")
            .orElse("Invalid username or password");
    }
}