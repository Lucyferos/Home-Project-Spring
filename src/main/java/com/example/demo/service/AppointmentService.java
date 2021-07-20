package com.example.demo.service;

import com.example.demo.dto.AppointmentCreateDTO;
import com.example.demo.model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment save(AppointmentCreateDTO appointmentCreateDTO);
    List<Appointment> getAllAppointments();
    List<Appointment> findByPesel(String pesel);
}
