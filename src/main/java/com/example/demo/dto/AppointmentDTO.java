package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private Long id;

    @JsonProperty("date")
    private LocalDateTime appointmentTime;

    @JsonProperty("patient")
    private PatientDTO patientDTO;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public AppointmentDTO(Long id, LocalDateTime appointmentTime, PatientDTO patientDTO, LocalDateTime createdAt) {
        this.id = id;
        this.appointmentTime = appointmentTime;
        this.patientDTO = patientDTO;
        this.createdAt = createdAt;
    }

    public AppointmentDTO() {
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public PatientDTO getPatientDTO() {
        return patientDTO;
    }

    public void setPatientDTO(PatientDTO patientDTO) {
        this.patientDTO = patientDTO;
    }
}
