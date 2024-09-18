package com.example.laboratory.EntityResolver.ProfileResolver;

import com.example.laboratory.Repository.NurseRepository;
import com.example.laboratory.models.Nurses;
import com.example.laboratory.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NurseResolver implements ProfileResolver{

    @Autowired
    private NurseRepository nursesRepository;

    @Override
    public Long getId() {
        Nurses nurses = new Nurses();
        return nurses.getId();
    }

    @Override
    public Long createProfile(Users user) {
        Nurses nurse = new Nurses();
        // set other fields
        return nursesRepository.save(nurse).getId();  // Save nurse profile in the database    }
    }
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