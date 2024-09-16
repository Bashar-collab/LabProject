package com.example.laboratory.EntityResolver.FeedbackResolver.Factory;

import com.example.laboratory.EntityResolver.FeedbackResolver.FeedbackResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackResolverFactory {
    private final Map<String, FeedbackResolver> resolvers = new HashMap<>();

    @Autowired
    public FeedbackResolverFactory(List<FeedbackResolver> resolverList) {
        for (FeedbackResolver resolver : resolverList) {
            resolvers.put(resolver.getFeedbackType(), resolver);
        }
    }

    public FeedbackResolver getResolver(String feedbackType) {
        return resolvers.get(feedbackType);
    }
}
