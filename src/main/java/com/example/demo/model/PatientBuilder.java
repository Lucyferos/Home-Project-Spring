package com.example.demo.model;

import com.example.demo.dto.AdressDTO;
import com.example.demo.dto.PatientDTO;

import java.time.LocalDateTime;

public class PatientBuilder {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private char sex;
    private String pesel;
    private LocalDateTime birthdate;
    private LocalDateTime createdAt;
    private Adress adress;

    public PatientBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PatientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PatientBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PatientBuilder setAge(Integer age) {
        this.age = age;
        return this;
    }

    public PatientBuilder setSex(char sex) {
        this.sex = sex;
        return this;
    }

    public PatientBuilder setPesel(String pesel) {
        this.pesel = pesel;
        return this;
    }

    public PatientBuilder setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public PatientBuilder setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PatientBuilder setAdress(Adress adress) {
        this.adress = adress;
        return this;
    }

    public Patient createPatient(){
        return new Patient(id, name, surname, age, sex, pesel, birthdate, createdAt, adress);
    }
}
