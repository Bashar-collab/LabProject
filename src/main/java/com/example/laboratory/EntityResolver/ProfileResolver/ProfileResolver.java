package com.example.laboratory.EntityResolver.ProfileResolver;

public interface ProfileResolver {
    String getProfileType(); // Method to return the entity type
    Object resolve(Long profileId); // Method to resolve the entity by ID
}
