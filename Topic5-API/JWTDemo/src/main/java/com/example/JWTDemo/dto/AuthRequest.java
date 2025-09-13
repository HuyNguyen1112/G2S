package com.example.JWTDemo.dto;

public record AuthRequest (
        String username,
        String password
)
{}
