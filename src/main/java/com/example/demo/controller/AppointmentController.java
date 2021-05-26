package com.example.demo.controller;

import com.example.demo.dto.AppointmentDTO;
import com.example.demo.mapper.AppointmentMapper;
import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/appointments")
@RestController
@CrossOrigin
public class AppointmentController {
    private AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;

    public AppointmentController(AppointmentService appointmentService, AppointmentMapper appointmentMapper) {
        this.appointmentService = appointmentService;
        this.appointmentMapper = appointmentMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO){
        Appointment appointment = appointmentService.save(appointmentMapper.appointmentDtoToAppointment(appointmentDTO));
        return ResponseEntity.ok(appointmentMapper.appointmentToAppointmentDto(appointment));
    }

    @GetMapping("/list")
    public ResponseEntity<List<AppointmentDTO>> listAppointments(){
        List<Appointment> appointmentList = new ArrayList<>(appointmentService.getAllAppointments());
        List<AppointmentDTO> appointmentDTOS = appointmentList.stream().map(appointmentMapper :: appointmentToAppointmentDto).collect(Collectors.toList());
        return ResponseEntity.ok(appointmentDTOS);
    }

    @PostMapping("/findPesel")
    public List<AppointmentDTO> findAppointmentWithPesel(@RequestParam String pesel){
        List<AppointmentDTO> appointmentList = appointmentService.findByPesel(pesel).stream().map(appointmentMapper :: appointmentToAppointmentDto).collect(Collectors.toList());
        return appointmentList;
    }
}
