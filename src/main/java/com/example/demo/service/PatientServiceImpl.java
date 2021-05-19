package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements  PatientService{
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> findAll() {
      return this.patientRepository.findAll();
    }

    @Override
    public Patient save(Patient patient) {
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public boolean deleteById(Long id) {
        patientRepository.deleteById(id);
        Patient check = patientRepository.findById(id).orElse(null);
        return check == null;
    }
}
