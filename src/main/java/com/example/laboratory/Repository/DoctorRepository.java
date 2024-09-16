package com.example.laboratory.Repository;

import com.example.laboratory.models.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctors, Long> {
    @Override
    Optional<Doctors> findById(Long aLong);
}
