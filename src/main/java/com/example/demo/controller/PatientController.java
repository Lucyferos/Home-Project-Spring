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
import java.util.stream.Collectors;

@RequestMapping("/api/Patient")
@RestController
@CrossOrigin
public class PatientController {
    private PatientService patientService;
    private final PatientMapper patientMapper;
    private AdressMapper adressMapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper, AdressMapper adressMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
        this.adressMapper = adressMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<HttpResponse<List<PatientDTO>>> getListedPatients() {
        List<Patient> listOfAllPatients = patientService.findAll();
        List<PatientDTO> patientDTOList = listOfAllPatients.stream().map((Patient patient) -> patientMapper.patientToPatientDto(patient, new CycleAvoidingMappingContext())).collect(Collectors.toList());
        return ResponseEntity.ok(new HttpResponse<>(HttpResponse.HttpResponseMessage.SUCCESS.getMessage(), patientDTOList));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpResponse<PatientDTO>> addPatient(@RequestBody PatientDTO patientDTO) {
        Patient newPatient = patientMapper.patientDtoToPatient(patientDTO, new CycleAvoidingMappingContext());
        return ResponseEntity.ok(new HttpResponse<>(HttpResponse.HttpResponseMessage.PATIENT_ADD_SUCCESS.getMessage(),patientMapper.patientToPatientDto(patientService.save(newPatient), new CycleAvoidingMappingContext())));
    }

    @PostMapping("/add/csv")
    public ResponseEntity<HttpResponse<List<PatientDTO>>> addPatientsThroughCsv(@RequestParam("file") MultipartFile multipartFile) throws WrongDateTimeException {
        try {
            List<Patient> listOfSingleParcelsWithCsvData = patientService.savePatientsFromCsv(multipartFile);
            return ResponseEntity.ok(new HttpResponse<>(HttpResponse.HttpResponseMessage.SUCCESS.getMessage(), listOfSingleParcelsWithCsvData.stream().map(patientMapper ::patientToPatientDtoForLists).collect(Collectors.toList())));
        }catch (DateTimeParseException e){
            throw new WrongDateTimeException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new HttpResponse<>(HttpResponse.HttpResponseMessage.FAIL.getMessage(), null));
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
