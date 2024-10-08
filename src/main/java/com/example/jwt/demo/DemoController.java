package com.example.jwt.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    @GetMapping
    public ResponseEntity<Map<String, String>> demo() {
        return ResponseEntity.ok(Map.of("message", "GET: This is a secured endpoint!"));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> demoPost() {
        return ResponseEntity.ok(Map.of("message", "POST: This is a secured endpoint!"));
    }
}
