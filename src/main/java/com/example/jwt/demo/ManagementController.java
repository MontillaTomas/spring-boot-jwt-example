package com.example.jwt.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/management")
public class ManagementController {
    @GetMapping
    public ResponseEntity<Map<String, String>> get() {
        return ResponseEntity.ok(Map.of("message", "GET: This is an management endpoint!"));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> post() {
        return ResponseEntity.ok(Map.of("message", "POST: This is an management endpoint!"));
    }
}
