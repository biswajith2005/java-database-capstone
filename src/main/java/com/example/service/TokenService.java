package com.example.service;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String generateToken(String email) {
        return "token";
    }
    public String getSigningKey() {
        return "secret";
    }
}
