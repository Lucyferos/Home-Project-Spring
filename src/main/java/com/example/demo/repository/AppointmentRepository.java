package com.example.demo.repository;

import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> , PagingAndSortingRepository<Appointment, Long> , CrudRepository<Appointment, Long> {
      @Query(value = "SELECT MIN(appointment.date) FROM appointment", nativeQuery = true)
      LocalDateTime getDate();

      Appointment findByAppointmentTime(LocalDateTime appointmentTime);
}
