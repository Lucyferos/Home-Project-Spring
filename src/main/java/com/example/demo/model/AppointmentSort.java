package com.example.demo.model;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class AppointmentSort {
    @Column(name = "id")
    private Long id;
    @Column(name = "min")
    private LocalDateTime min;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "patient_id")
    private Patient patientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getMin() {
        return min;
    }

    public void setMin(LocalDateTime min) {
        this.min = min;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }
}
