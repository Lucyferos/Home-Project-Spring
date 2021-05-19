package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PatientDTO {

    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("sex")
    private char sex;

    @JsonProperty("pesel")
    private String pesel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("birthdate")
    private LocalDateTime birthdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("adress")
    private AdressDTO adressDTO;

    public PatientDTO(Long id, String name, String surname, Integer age, char sex,
                      String pesel, LocalDateTime birthdate, LocalDateTime createdAt, AdressDTO adressDTO) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.pesel = pesel;
        this.birthdate = birthdate;
        this.createdAt = createdAt;
        this.adressDTO = adressDTO;
    }

    public Long getId() {
        return id;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public AdressDTO getAdressDTO() {
        return adressDTO;
    }

    public void setAdressDTO(AdressDTO adressDTO) {
        this.adressDTO = adressDTO;
    }
}
