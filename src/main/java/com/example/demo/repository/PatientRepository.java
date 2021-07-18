package com.example.demo.repository;

import com.example.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> , CrudRepository<Patient, Long> , PagingAndSortingRepository<Patient, Long> {
    @Query("SELECT Count(*) FROM  Patient " +
            "where created_at >= CURRENT_DATE()- 30")
    Long numberOfContactsAddedInLast30Days();

    @Override
    long count();
}
