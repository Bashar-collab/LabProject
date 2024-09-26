package com.example.laboratory.EntityResolver.ProfileResolver.Factory;

import com.example.laboratory.EntityResolver.ProfileResolver.ProfileResolver;
import com.example.laboratory.Service.TestCategoryService;
import com.example.laboratory.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProfileResolverFactory {
    private final Map<String, ProfileResolver> resolvers = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(TestCategoryService.class);

    @Autowired
    public ProfileResolverFactory(List<ProfileResolver> resolverList) {
        for (ProfileResolver resolver : resolverList) {
            logger.info("Registering resolver for profile type: {}", resolver.getProfileType());
            resolvers.put(resolver.getProfileType(), resolver);
        }
    }

    public Long createProfile(Users user) {
        logger.info("Profile Type: {}", user.getProfileType());
        ProfileResolver resolver = resolvers.get(user.getProfileType());
        if (resolver != null) {
            logger.info("Resolver profile Type: {}", user.getProfileType());
            return resolver.createProfile(user);
        } else {
            throw new IllegalArgumentException("Invalid profile type: " + user.getProfileType());
        }

//        logger.info(user.getProfileType());
//        ProfileResolver resolver = resolvers.get(user.getProfileType());
//        if (resolver == null) {
//            throw new IllegalArgumentException("Invalid profile type: " + user.getProfileType());
//        }
//        return resolver.createProfile(user);
    }
    public ProfileResolver getResolver(String profileType) {
        return resolvers.get(profileType);
    }
}
