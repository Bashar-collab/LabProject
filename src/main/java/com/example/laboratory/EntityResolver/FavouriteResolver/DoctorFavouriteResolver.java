package com.example.laboratory.EntityResolver.FavouriteResolver;

import org.springframework.stereotype.Component;

@Component
public class DoctorFavouriteResolver implements FavouriteResolver{
    @Override
    public String getFavouriteType() {
        return "Doctor";
    }

    @Override
    public Object resolve(Long favouriteId) {
        return "Resolved Doctor with ID: " + favouriteId;
    }
}
