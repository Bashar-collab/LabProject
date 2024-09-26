package com.example.laboratory.EntityResolver.ProfileResolver;

import com.example.laboratory.Repository.LabEmployeesRepository;
import com.example.laboratory.models.LabEmployees;
import com.example.laboratory.models.Labs;
import com.example.laboratory.models.Nurses;
import com.example.laboratory.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class LabEmployeeResolver implements ProfileResolver{

    @Autowired
    private LabEmployeesRepository labEmployeesRepository;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public Long createProfile(Users user) {
        LabEmployees labEmployees = new LabEmployees();
        // set other fields
        return labEmployeesRepository.save(labEmployees).getId();  // Save Lab employee profile in the database    };
    }

    @Override
    public String getProfileType()
    {
        return "LabEmployee";
    }

    @Override
    public Object resolve(Long profileId)
    {
        return labEmployeesRepository.findById(profileId).orElse(null);
    }
}
