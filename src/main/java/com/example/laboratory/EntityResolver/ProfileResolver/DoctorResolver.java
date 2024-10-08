package com.example.laboratory.EntityResolver.ProfileResolver;

import com.example.laboratory.Repository.DoctorRepository;
import com.example.laboratory.models.Doctors;
import com.example.laboratory.models.Nurses;
import com.example.laboratory.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.print.Doc;

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
        Doctors doctors = new Doctors();
        return doctorRepository.save(doctors).getId();  // Save Doctor profile in the database    };
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
