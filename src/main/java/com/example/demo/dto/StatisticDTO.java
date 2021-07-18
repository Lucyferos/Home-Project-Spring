package com.example.demo.dto;

public class StatisticDTO {
    private long allPatientsInDatabase;
    private long patientsAddedInLast30Days;

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
