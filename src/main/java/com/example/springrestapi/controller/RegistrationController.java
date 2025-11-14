package com.example.springrestapi.controller;

import com.example.springrestapi.service.UserService;
import com.example.springrestapi.service.PasswordValidator;
import com.example.springrestapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password");

        Map<String, String> response = new HashMap<>();

        if (username == null || username.isEmpty()) {
            response.put("error", "Username is required");
            return ResponseEntity.badRequest().body(response);
        }

        if (email == null || email.isEmpty()) {
            response.put("error", "Email is required");
            return ResponseEntity.badRequest().body(response);
        }

        if (password == null || password.isEmpty()) {
            response.put("error", "Password is required");
            return ResponseEntity.badRequest().body(response);
        }

        String passwordError = PasswordValidator.getValidationErrorMessage(password);
        if (passwordError != null) {
            response.put("error", passwordError);
            return ResponseEntity.badRequest().body(response);
        }

        try {
            User user = userService.registerUser(username, email, password);
            response.put("message", "User registered successfully");
            response.put("userId", user.getId().toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
