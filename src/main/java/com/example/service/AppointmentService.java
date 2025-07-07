package com.example.service;

import com.example.model.Appointment;
import com.example.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment bookAppointment(Long patientId, Long doctorId, 
                                       LocalDate date, LocalTime time, 
                                       String reason) {
        // Validate appointment parameters
        if (patientId == null || doctorId == null || date == null || time == null) {
            throw new IllegalArgumentException("Invalid appointment parameters");
        }
        
        // Check availability (would be more complex in real implementation)
        if (!isSlotAvailable(doctorId, date, time)) {
            throw new IllegalStateException("Time slot not available");
        }
        
        // Create and save appointment
        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setReason(reason);
        appointment.setStatus("BOOKED");
        
        return appointmentRepository.save(appointment);
    }

  
    public List<Appointment> getAppointments(Long doctorId, LocalDate date) {
        if (doctorId != null && date != null) {
            return appointmentRepository.findByDoctorIdAndDate(doctorId, date);
        } else if (doctorId != null) {
            return appointmentRepository.findByDoctorId(doctorId);
        } else if (date != null) {
            return appointmentRepository.findByDate(date);
        } else {
            return appointmentRepository.findAll();
        }
    }

    private boolean isSlotAvailable(Long doctorId, LocalDate date, LocalTime time) {
        // In a real implementation, this would check against existing appointments
        return appointmentRepository.findByDoctorIdAndDateAndTime(doctorId, date, time).isEmpty();
    }
}
