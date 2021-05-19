package com.example.demo.controller;

import com.example.demo.dto.HttpResponse;
import com.example.demo.dto.PatientDTO;
import com.example.demo.mapper.AdressMapper;
import com.example.demo.mapper.CycleAvoidingMappingContext;
import com.example.demo.mapper.PatientMapper;
import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse<Object>> deletePatient(@PathVariable Long id) {
        if (patientService.deleteById(id)) {
            return ResponseEntity.ok(new HttpResponse<>(HttpResponse.HttpResponseMessage.SUCCESS.getMessage(), null));
        } else {
            return ResponseEntity.ok(new HttpResponse<>(HttpResponse.HttpResponseMessage.FAIL.getMessage(), null));
        }
    }
}
