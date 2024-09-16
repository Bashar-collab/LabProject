package com.example.laboratory.Repository;

import com.example.laboratory.models.LabEmployees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabEmployeesRepository extends JpaRepository<LabEmployees, Long> {
    @Override
    Optional<LabEmployees> findById(Long aLong);
}
