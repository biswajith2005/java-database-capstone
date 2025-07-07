package com.example.repository;

import com.example.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    // Find appointments by doctor ID
    List<Appointment> findByDoctorId(Long doctorId);
    
    // Find appointments by date
    List<Appointment> findByDate(LocalDate date);
    
    // Find appointments by doctor ID and date
    List<Appointment> findByDoctorIdAndDate(Long doctorId, LocalDate date);
    
    // Find specific appointment by doctor, date and time
    List<Appointment> findByDoctorIdAndDateAndTime(Long doctorId, LocalDate date, LocalTime time);
}
