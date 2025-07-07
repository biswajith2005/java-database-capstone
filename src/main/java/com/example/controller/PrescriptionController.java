package com.example.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PrescriptionController {
    @PostMapping("/prescriptions")
    public ResponseEntity<?> savePrescription(
        @RequestHeader("Authorization") String token,
        @RequestBody Object prescription
    ) {
        return ResponseEntity.ok("Prescription saved");
    }
}
