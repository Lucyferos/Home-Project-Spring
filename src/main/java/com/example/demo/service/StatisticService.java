package com.example.demo.service;

import com.example.demo.dto.StatisticDTO;
import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public StatisticService(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public StatisticDTO getDatabaseStatistic(){
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setAllPatientsInDatabase(getAllPatientsInDatabase());
        statisticDTO.setLatestAppointment(getEarliestAppointment());
        statisticDTO.setPatientsAddedInLast30Days(getLastPatientsFrom30Days());
        return statisticDTO;
    }

    private Appointment getLatestAppointment(){
        return appointmentRepository.findByAppointmentTime(appointmentRepository.getDate());
    }

    private Appointment getEarliestAppointment(){
        return appointmentRepository.findEarliestAppointment();
    }

    private long getAllPatientsInDatabase(){
        return patientRepository.count();
    }

    private long getLastPatientsFrom30Days(){
        return patientRepository.numberOfContactsAddedInLast30Days();
    }
}
