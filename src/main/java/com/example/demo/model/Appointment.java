package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date")
    private LocalDateTime appointmentTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id" ,referencedColumnName = "id")
    private Patient patient;


    public Appointment(Patient patient, LocalDateTime appointmentTime, LocalDateTime createdAt) {
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.createdAt = createdAt;
    }

    public Appointment() {

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
