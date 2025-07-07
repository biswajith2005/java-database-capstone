package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @GetMapping("/availability/{userType}/{doctorId}/{token}")
    public ResponseEntity<String> getDoctorAvailability(
            @PathVariable("userType") String userType,
            @PathVariable("doctorId") Long doctorId,
            @PathVariable("token") String token,
            @RequestParam(value = "date", required = false) String date) {
        
        // Validate token (in a real implementation, this would use a service)
        if (!isValidToken(token)) {
            return ResponseEntity.status(401).body("Unauthorized: Invalid token");
        }

        // Check user permissions
        if (!hasPermission(userType, "VIEW_AVAILABILITY")) {
            return ResponseEntity.status(403).body("Forbidden: Insufficient privileges");
        }

        // Fetch availability (simulated response)
        String availabilityData = fetchDoctorAvailability(doctorId, date);
        
        return ResponseEntity.ok(availabilityData);
    }

    // Simulated validation method
    private boolean isValidToken(String token) {
        // In a real implementation, this would validate against a security service
        return token != null && token.startsWith("AUTH_");
    }

    // Simulated permission check
    private boolean hasPermission(String userType, String permission) {
        // Real implementation would use role-based access control
        return "admin".equalsIgnoreCase(userType) || 
               "doctor".equalsIgnoreCase(userType);
    }

    // Simulated data service
    private String fetchDoctorAvailability(Long doctorId, String date) {
        // Real implementation would fetch from database
        return String.format("{\"doctorId\": %d, \"date\": \"%s\", \"slots\": [\"09:00\", \"11:00\", \"14:00\"]}", 
                            doctorId, date != null ? date : "2023-10-15");
    }
}
