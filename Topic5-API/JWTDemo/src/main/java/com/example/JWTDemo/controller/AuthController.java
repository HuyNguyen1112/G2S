package com.example.JWTDemo.controller;

import com.example.JWTDemo.dto.AuthRequest;
import com.example.JWTDemo.dto.AuthResponse;
import com.example.JWTDemo.dto.RegisterRequest;
import com.example.JWTDemo.dto.RegisterResponse;
import com.example.JWTDemo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register (@RequestBody RegisterRequest req) {
        return ResponseEntity.ok(service.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody AuthRequest req) {
        return ResponseEntity.ok(service.login(req));
    }
}
