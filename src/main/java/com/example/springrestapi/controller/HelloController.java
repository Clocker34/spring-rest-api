package com.example.springrestapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping
    public String hello() {
        return "Hello from Spring Boot REST API!";
    }

    @GetMapping("/{name}")
    public String greet(@PathVariable String name) {
        return "Hello, " + name + "! Welcome to Spring Boot REST API.";
    }
}
