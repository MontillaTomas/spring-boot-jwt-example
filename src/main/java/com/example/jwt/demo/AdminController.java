package com.example.jwt.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @GetMapping
    //@PreAuthorize("hasAuthority('admin:read')") This is another way to authorize based on permission
    public ResponseEntity<Map<String, String>> get() {
        return ResponseEntity.ok(Map.of("message", "GET: This is an admin endpoint!"));
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<Map<String, String>> post() {
        return ResponseEntity.ok(Map.of("message", "POST: This is an admin endpoint!"));
    }
}
