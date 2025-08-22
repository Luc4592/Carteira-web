package com.example.portfolio.controller;

import com.example.portfolio.dto.LoginRequest;
import com.example.portfolio.model.User;
import com.example.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .map(user -> {
                    if (user.getPassword().equals(request.getPassword())) {
                        Map<String, String> response = new HashMap<>();
                        response.put("token", "fake-jwt-token");
                        return ResponseEntity.ok(response);
                    } else {
                        Map<String, String> response = new HashMap<>();
                        response.put("error", "Usuário ou senha inválidos");
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                    }
                })
                .orElseGet(() -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("error", "Usuário ou senha inválidos");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                });
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok("Usuário cadastrado com sucesso");
    }
}