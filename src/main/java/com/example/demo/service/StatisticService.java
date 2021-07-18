package com.example.demo.service;

import com.example.demo.dto.StatisticDTO;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {
    private final PatientRepository patientRepository;

    public StatisticService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public StatisticDTO getDatabaseStatistic(){
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setAllPatientsInDatabase(getAllPatientsInDatabase());
        statisticDTO.setPatientsAddedInLast30Days(getLastPatientsFrom30Days());
        return statisticDTO;
    }

    private long getAllPatientsInDatabase(){
        return patientRepository.count();
    }

    private long getLastPatientsFrom30Days(){
        return patientRepository.numberOfContactsAddedInLast30Days();
    }
}
