package com.example.service;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    public Object getAvailableSlots(Long doctorId, String date) {
        return "Available slots";
    }
    public Object validateLogin(String email, String password) {
        return "Login valid";
    }
}
