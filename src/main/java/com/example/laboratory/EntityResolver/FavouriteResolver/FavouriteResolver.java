package com.example.laboratory.EntityResolver.FavouriteResolver;

public interface FavouriteResolver {
    String getFavouriteType(); // Method to return the entity type
    Object resolve(Long favouriteId); // Method to resolve the entity by ID
}
