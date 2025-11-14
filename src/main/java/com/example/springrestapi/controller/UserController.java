package com.example.springrestapi.controller;

import com.example.springrestapi.model.User;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // In-memory storage for demo purposes
    private static List<User> users = new ArrayList<>();
    private static int idCounter = 1;

    static {
        users.add(new User(idCounter++, "John Doe", "john@example.com"));
        users.add(new User(idCounter++, "Jane Smith", "jane@example.com"));
    }

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        return user.orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(idCounter++);
        users.add(user);
        return user;
    }
}
