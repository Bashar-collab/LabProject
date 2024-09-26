package com.example.laboratory.EntityResolver.ProfileResolver;

import com.example.laboratory.Repository.NurseRepository;
import com.example.laboratory.Service.TestCategoryService;
import com.example.laboratory.models.Nurses;
import com.example.laboratory.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NurseResolver implements ProfileResolver{

    @Autowired
    private NurseRepository nursesRepository;

    private static final Logger logger = LoggerFactory.getLogger(TestCategoryService.class);


    @Override
    public Long getId() {
        return null;
    }

    @Override
    public Long createProfile(Users user) {
        Nurses nurses = new Nurses();
        // set other fields
        return nursesRepository.save(nurses).getId();  // Save nurse profile in the database
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