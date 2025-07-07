package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Object, Long> {
    Optional<Object> findByEmail(String email);
    Optional<Object> findByEmailOrPhoneNumber(String email, String phone);
}
