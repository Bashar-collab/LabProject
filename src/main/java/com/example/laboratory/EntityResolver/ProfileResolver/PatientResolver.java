package com.example.laboratory.EntityResolver.ProfileResolver;

import com.example.laboratory.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PatientResolver implements ProfileResolver{

    @Autowired
    private PatientRepository patientRepository;

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
