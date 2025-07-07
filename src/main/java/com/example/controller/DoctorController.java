package com.example.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @GetMapping("/availability")
    public ResponseEntity<?> getAvailability(
        @RequestParam String date,
        @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok("Doctor availability");
    }
}
