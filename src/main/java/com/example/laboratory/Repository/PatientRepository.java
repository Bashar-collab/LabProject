package com.example.laboratory.Repository;

import com.example.laboratory.models.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patients, Long> {
    @Override
    Optional<Patients> findById(Long aLong);
}
