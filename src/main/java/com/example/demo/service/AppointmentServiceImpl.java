package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        appointment.setCreatedAt(LocalDateTime.now());
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Appointment> findByPesel(String pesel){
       List<Appointment> appointmentList = getAllAppointments();
       List<Appointment> appointmentWithMatchedPesel = new ArrayList<>();
       for(Appointment currentAppointment : appointmentList){
           try{
               if(currentAppointment.getPatient().getPesel().equals(pesel)){
                   appointmentWithMatchedPesel.add(currentAppointment);
               }
           }catch (NullPointerException e){
               System.out.println(e);
           }
       }
       return appointmentWithMatchedPesel;
    }
}
