package com.example.demo.service;

import com.example.demo.model.Adress;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements  PatientService{
    private PatientRepository patientRepository;

    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


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

    @Override
    public List<Patient> savePatientsFromCsv(MultipartFile multipartFile) throws IOException {
        List<List<String>> listOfCsvFiles = new CsvFilesHandler().csvReader(multipartFile);
        List<Patient> listOfPatients = new ArrayList<>();
        for(int i = 0 ; i < listOfCsvFiles.size(); i++){
            List<String> listOfAdress = listOfCsvFiles.get(i).subList(0,4);
            List<String> listOfPatientParameteres = listOfCsvFiles.get(i).subList(4,11);
            Patient patient = saveDataFromCsvToPatient(new Patient(), listOfAdress , listOfPatientParameteres);
            listOfPatients.add(patient);
            patientRepository.save(patient);
        }
        return listOfPatients;
    }

    private Patient saveDataFromCsvToPatient(Patient patient, List<String> listOfAdress , List<String> listOfPatientParameteres){
        /**PatientParameters Setting **/
        patient.setAge(Integer.valueOf(listOfPatientParameteres.get(0)));
        patient.setBirthdate(LocalDateTime.parse(listOfPatientParameteres.get(1), formatter));patient.setCreatedAt(LocalDateTime.parse(listOfPatientParameteres.get(2), formatter));
        patient.setName(listOfPatientParameteres.get(3));
        patient.setPesel(listOfPatientParameteres.get(4));
        patient.setSex(listOfPatientParameteres.get(5).charAt(0));
        patient.setSurname(listOfPatientParameteres.get(6));
        patient.setAdress(adressFromListSetter(new Adress(), listOfAdress));

        return patient;
    }

    private Adress adressFromListSetter(Adress adress , List<String> listOfAdress){
        adress.setCity(listOfAdress.get(0));
        adress.setCountry(listOfAdress.get(1));
        adress.setStreet(listOfAdress.get(2));
        adress.setZip(listOfAdress.get(3));
        return adress;
    }
}
