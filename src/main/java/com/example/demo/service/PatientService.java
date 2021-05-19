package com.example.demo.service;

import com.example.demo.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> findAll();
    Patient save(Patient patient);
    boolean deleteById(Long id);
}
