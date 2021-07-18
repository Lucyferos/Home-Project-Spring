package com.example.demo.service;

import com.example.demo.dto.AppointmentCreateDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public Appointment save(AppointmentCreateDTO appointmentCreateDTO) {
        Appointment appointment = new Appointment();
        Optional<Patient> patient = patientRepository.findById(appointmentCreateDTO.getPatientId());
        patient.ifPresent(appointment::setPatient);
        appointment.setAppointmentTime(appointmentCreateDTO.getAppointmentDate());
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
