package com.example.demo.mapper;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;

public interface PatientMapper {
    Patient patientDtoToPatient(PatientDTO patientDTO, CycleAvoidingMappingContext cycleAvoidingMappingContext);
    PatientDTO patientToPatientDto(Patient patient , CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
