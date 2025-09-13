package com.example.JWTDemo.service;

import com.example.JWTDemo.dto.AuthRequest;
import com.example.JWTDemo.dto.AuthResponse;
import com.example.JWTDemo.dto.RegisterRequest;
import com.example.JWTDemo.dto.RegisterResponse;
import com.example.JWTDemo.entity.Role;
import com.example.JWTDemo.entity.User;
import com.example.JWTDemo.exception.InvalidCredentialsException;
import com.example.JWTDemo.exception.ResourceNotFoundException;
import com.example.JWTDemo.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final JwtService jwt;

    public AuthService(UserRepository userRepo, JwtService jwt) {
        this.userRepo = userRepo;
        this.jwt = jwt;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest req) {
        if(userRepo.existsByUsername(req.username()))
            throw new ResourceNotFoundException("Username already exists");

        User u = new User();
        u.setUsername(req.username());
        u.setPassWordHash(BCrypt.hashpw(req.password(), BCrypt.gensalt()));
        u.setRole(req.role() == null ? Role.USER: req.role());
        User savedUser= userRepo.save(u);

        return new RegisterResponse(savedUser.getUsername(),savedUser.getRole().toString());
    }

    @Transactional(readOnly = true)
    public AuthResponse login (AuthRequest req) {
        var u = userRepo.findByUsername(req.username())
                .orElseThrow(()->new InvalidCredentialsException("Invalid credentials"));

        if(!BCrypt.checkpw(req.password(), u.getPassWordHash()))
            throw  new InvalidCredentialsException("Invalid credentials");

        return new AuthResponse(jwt.generateToken(u));
    }
}
