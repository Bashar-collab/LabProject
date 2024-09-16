package com.example.laboratory.EntityResolver.FavouriteResolver;

import org.springframework.stereotype.Component;

@Component
public class NurseFavouriteResolver implements FavouriteResolver{
    @Override
    public String getFavouriteType() {
        return "Nurse";
    }

    @Override
    public Object resolve(Long favouriteId) {
        return "Resolved Nurse with ID: " + favouriteId;
    }
}
