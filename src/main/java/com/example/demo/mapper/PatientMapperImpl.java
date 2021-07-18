package com.example.demo.mapper;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.model.PatientBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
@Component
public class PatientMapperImpl implements PatientMapper{

    private AdressMapper adressMapper;

    public PatientMapperImpl(AdressMapper adressMapper) {
        this.adressMapper = adressMapper;
    }

    @Override
    public Patient patientDtoToPatient(PatientDTO patientDTO, CycleAvoidingMappingContext cycleAvoidingMappingContext){
        Patient target = cycleAvoidingMappingContext.getMappedInstance( patientDTO, Patient.class );
        if ( target != null ) {
            return target;
        }

        if(patientDTO == null){
            return null;
        }
        Patient patient = new PatientBuilder()
                .setAdress(adressMapper.adressDtoToAdress(patientDTO.getAdressDTO()))
                .setAge(patientDTO.getAge())
                .setBirthdate(patientDTO.getBirthdate())
                .setCreatedAt(patientDTO.getCreatedAt())
                .setId(patientDTO.getId())
                .setName(patientDTO.getName())
                .setPesel(patientDTO.getPesel())
                .setSex(patientDTO.getSex())
                .setSurname(patientDTO.getSurname())
                .createPatient();
        cycleAvoidingMappingContext.storeMappedInstance( patientDTO, patient );
        return patient;
    }

    @Override
    public PatientDTO patientToPatientDtoForLists(Patient patient) {
        if(patient == null){
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setAdressDTO(adressMapper.adressToAdressDTO(patient.getAdress()));
        patientDTO.setAge(patient.getAge());
        patientDTO.setBirthdate(patient.getBirthdate());
        patientDTO.setPesel(patient.getPesel());
        patientDTO.setId(patient.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setCreatedAt(patient.getCreatedAt());
        patientDTO.setSurname(patient.getSurname());
        patientDTO.setSex(patient.getSex());
        return patientDTO;
    }

    @Override
    public PatientDTO patientToPatientDto(Patient patient, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        PatientDTO target = cycleAvoidingMappingContext.getMappedInstance( patient, PatientDTO.class );
        if ( target != null ) {
            return target;
        }

        if(patient == null){
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setAdressDTO(adressMapper.adressToAdressDTO(patient.getAdress()));
        patientDTO.setAge(patient.getAge());
        patientDTO.setBirthdate(patient.getBirthdate());
        patientDTO.setPesel(patient.getPesel());
        patientDTO.setId(patient.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setCreatedAt(patient.getCreatedAt());
        patientDTO.setSurname(patient.getSurname());
        patientDTO.setSex(patient.getSex());
        return patientDTO;
    }
}
