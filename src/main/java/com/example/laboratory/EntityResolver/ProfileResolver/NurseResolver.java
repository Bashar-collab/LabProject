package com.example.laboratory.EntityResolver.ProfileResolver;

import com.example.laboratory.Repository.NursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class NurseResolver implements ProfileResolver{

    @Autowired
    private NursesRepository nursesRepository;

    @Override
    public String getProfileType()
    {
        return "Nurse";
    }

    @Override
    public Object resolve(Long profileId)
    {
        return nursesRepository.findById(profileId).orElse(null);
    }
}