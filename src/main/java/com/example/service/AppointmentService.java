package com.example.service;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    public void bookAppointment(Object appointment) {}
    public Object getAppointments(Long doctorId, String date) {
        return "Appointments";
    }
}
