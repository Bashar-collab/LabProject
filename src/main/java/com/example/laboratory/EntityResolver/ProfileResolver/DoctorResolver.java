package com.example.laboratory.EntityResolver.ProfileResolver;

import com.example.laboratory.Repository.DoctorRepository;
import com.example.laboratory.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class DoctorResolver implements ProfileResolver{

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public Long createProfile(Users user) {
        return null;
    }

    @Override
    public String getProfileType()
    {
        return "Doctor";
    }

    @Override
    public Object resolve(Long profileId)
    {
        return doctorRepository.findById(profileId).orElse(null);
    }
}
