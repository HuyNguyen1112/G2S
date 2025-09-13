package com.example.JWTDemo.dto;

import com.example.JWTDemo.entity.Role;

public record RegisterRequest(
        String username,
        String password,
        Role role
) {}
