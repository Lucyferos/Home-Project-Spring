package com.example.demo.dto;

import com.example.demo.model.Appointment;

import java.util.List;

public class StatisticDTO {
    private long allPatientsInDatabase;
    private long patientsAddedInLast30Days;

    private List<Appointment> listOf10EarliestAppointments;

    private Appointment latestAppointment;

    public Appointment getLatestAppointment() {
        return latestAppointment;
    }

    public void setLatestAppointment(Appointment latestAppointment) {
        this.latestAppointment = latestAppointment;
    }

    public List<Appointment> getListOf10EarliestAppointments() {
        return listOf10EarliestAppointments;
    }

    public void setListOf10EarliestAppointments(List<Appointment> listOf10EarliestAppointments) {
        this.listOf10EarliestAppointments = listOf10EarliestAppointments;
    }

    public long getAllPatientsInDatabase() {
        return allPatientsInDatabase;
    }

    public void setAllPatientsInDatabase(long allPatientsInDatabase) {
        this.allPatientsInDatabase = allPatientsInDatabase;
    }

    public long getPatientsAddedInLast30Days() {
        return patientsAddedInLast30Days;
    }

    public void setPatientsAddedInLast30Days(long patientsAddedInLast30Days) {
        this.patientsAddedInLast30Days = patientsAddedInLast30Days;
    }
}
