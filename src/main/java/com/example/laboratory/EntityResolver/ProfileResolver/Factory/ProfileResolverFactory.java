package com.example.laboratory.EntityResolver.ProfileResolver.Factory;

import com.example.laboratory.EntityResolver.ProfileResolver.ProfileResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProfileResolverFactory {
    private final Map<String, ProfileResolver> resolvers = new HashMap<>();

    @Autowired
    public ProfileResolverFactory(List<ProfileResolver> resolverList) {
        for (ProfileResolver resolver : resolverList) {
            resolvers.put(resolver.getProfileType(), resolver);
        }
    }

    public ProfileResolver getResolver(String profileType) {
        return resolvers.get(profileType);
    }
}
