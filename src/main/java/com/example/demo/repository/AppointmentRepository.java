package com.example.demo.repository;

import com.example.demo.model.Appointment;
import com.example.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> , PagingAndSortingRepository<Appointment, Long> , CrudRepository<Appointment, Long> {
}
