package com.example.laboratory.EntityResolver.FavouriteResolver.Factory;

import com.example.laboratory.EntityResolver.FavouriteResolver.FavouriteResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FavouriteResolverFactory {
    private final Map<String, FavouriteResolver> resolvers = new HashMap<>();

    @Autowired
    public FavouriteResolverFactory(List<FavouriteResolver> resolverList) {
        for (FavouriteResolver resolver : resolverList) {
            resolvers.put(resolver.getFavouriteType(), resolver);
        }
    }

    public FavouriteResolver getResolver(String favouriteType) {
        return resolvers.get(favouriteType);
    }
}
