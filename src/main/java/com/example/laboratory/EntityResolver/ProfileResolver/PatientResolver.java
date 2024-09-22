package com.example.laboratory.EntityResolver.ProfileResolver;

import com.example.laboratory.Repository.PatientRepository;
import com.example.laboratory.models.Patients;
import com.example.laboratory.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PatientResolver implements ProfileResolver{

    @Autowired
    private PatientRepository patientRepository;

    private Patients patients;
    @Override
    public Long getId() {
        return patients.getId();
    }

    @Override
    public Long createProfile(Users user) {
        // set other fields
        return patientRepository.save(patients).getId();  // Save patient profile in the database
    }

    @Override
    public String getProfileType()
    {
        return "Patient";
    }

    @Override
    public Object resolve(Long profileId)
    {
        return patientRepository.findById(profileId).orElse(null);
    }
}
