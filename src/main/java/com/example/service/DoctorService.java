package com.example.service;

import com.example.model.Doctor;
import com.example.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentService appointmentService;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, 
                         AppointmentService appointmentService) {
        this.doctorRepository = doctorRepository;
        this.appointmentService = appointmentService;
    }

    /**
     * Retrieves available time slots for a doctor on a specific date
     * 
     * @param doctorId ID of the doctor
     * @param date Date to check availability
     * @return List of available time slots
     */
    public List<LocalTime> getAvailableSlots(Long doctorId, LocalDate date) {
        // Validate inputs
        if (doctorId == null || date == null) {
            throw new IllegalArgumentException("Doctor ID and date are required");
        }
        
        // Check if doctor exists
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if (doctor.isEmpty()) {
            throw new IllegalArgumentException("Doctor not found");
        }
        
        // Get doctor's schedule
        LocalTime startTime = doctor.get().getShiftStart();
        LocalTime endTime = doctor.get().getShiftEnd();
        
        // Generate possible slots (every 30 minutes)
        List<LocalTime> allSlots = generateTimeSlots(startTime, endTime, 30);
        
        // Get booked appointments
        List<LocalTime> bookedSlots = appointmentService.getBookedSlots(doctorId, date);
        
        // Filter out booked slots
        allSlots.removeAll(bookedSlots);
        
        return allSlots;
    }

    /**
     * Validates doctor login credentials
     * 
     * @param email Doctor's email
     * @param password Password
     * @return Doctor object if valid, null otherwise
     */
    public Doctor validateLogin(String email, String password) {
        if (email == null || password == null) {
            return null;
        }
        
        Doctor doctor = doctorRepository.findByEmail(email);
        if (doctor != null && doctor.getPassword().equals(password)) {
            return doctor;
        }
        return null;
    }

    /**
     * Generates time slots between start and end times
     * 
     * @param start Start time
     * @param end End time
     * @param interval Minutes between slots
     * @return List of time slots
     */
    private List<LocalTime> generateTimeSlots(LocalTime start, LocalTime end, int interval) {
        List<LocalTime> slots = new ArrayList<>();
        LocalTime current = start;
        
        while (current.isBefore(end)) {
            slots.add(current);
            current = current.plusMinutes(interval);
        }
        
        return slots;
    }
}
