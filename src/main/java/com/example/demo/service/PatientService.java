package com.example.demo.service;

import com.example.demo.model.Patient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PatientService {
    List<Patient> findAll();
    Patient save(Patient patient);
    boolean deleteById(Long id);
    List<Patient> savePatientsFromCsv(MultipartFile multipartFile) throws IOException;
}
