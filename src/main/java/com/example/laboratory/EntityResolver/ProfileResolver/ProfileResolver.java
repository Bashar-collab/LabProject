package com.example.laboratory.EntityResolver.ProfileResolver;

import com.example.laboratory.models.Users;

public interface ProfileResolver {
    Long getId();
    Long createProfile(Users user);
    String getProfileType(); // Method to return the entity type
    Object resolve(Long profileId); // Method to resolve the entity by ID
}
