package com.example.demo.mapper;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.model.PatientBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-03-11T19:21:44+0100",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)

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
}
