package com.example.laboratory.Repository;

import com.example.laboratory.models.Nurses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NursesRepository extends JpaRepository<Nurses, Long> {
    @Override
    Optional<Nurses> findById(Long aLong);
}
