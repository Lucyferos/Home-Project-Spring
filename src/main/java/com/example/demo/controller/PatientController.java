package com.example.demo.controller;

import com.example.demo.dto.HttpResponse;
import com.example.demo.dto.PatientDTO;
import com.example.demo.exception.WrongDateTimeException;
import com.example.demo.mapper.AdressMapper;
import com.example.demo.mapper.CycleAvoidingMappingContext;
import com.example.demo.mapper.PatientMapper;
import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;

@RequestMapping("/api/Patient")
@RestController
@CrossOrigin
public class PatientController {
    private PatientService patientService;
    private PatientMapper patientMapper;
    private AdressMapper adressMapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper, AdressMapper adressMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
        this.adressMapper = adressMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Patient>> getListedPatients() {
        List<Patient> listOfAllPatients = patientService.findAll();
        return ResponseEntity.ok(listOfAllPatients);
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody PatientDTO patientDTO) {
        Patient newPatient = patientMapper.patientDtoToPatient(patientDTO, new CycleAvoidingMappingContext());
        return ResponseEntity.ok(patientService.save(newPatient));
    }

    @PostMapping("/add/csv")
    public ResponseEntity<HttpResponse<Object>> addPatientsThroughCsv(@RequestParam("file") MultipartFile multipartFile) throws IOException , WrongDateTimeException {
        try {
            List<Patient> listOfSingleParcelsWithCsvData = patientService.savePatientsFromCsv(multipartFile);
        }catch (DateTimeParseException e){
            throw new WrongDateTimeException();
        }
        return ResponseEntity.ok(new HttpResponse<>(HttpResponse.HttpResponseMessage.SUCCESS.getMessage(), null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse<Object>> deletePatient(@PathVariable Long id) {
        if (patientService.deleteById(id)) {
            return ResponseEntity.ok(new HttpResponse<>(HttpResponse.HttpResponseMessage.SUCCESS.getMessage(), null));
        } else {
            return ResponseEntity.ok(new HttpResponse<>(HttpResponse.HttpResponseMessage.FAIL.getMessage(), null));
        }
    }
}
