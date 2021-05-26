package com.example.demo.service;

import com.example.demo.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment save(Appointment appointment);
    List<Appointment> getAllAppointments();
    List<Appointment> findByPesel(String pesel);
}
