package com.example.laboratory.EntityResolver.NotificationResolver.Factory;

import com.example.laboratory.EntityResolver.NotificationResolver.NotificationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationResolverFactory {
    private final Map<String, NotificationResolver> resolvers = new HashMap<>();

    @Autowired
    public NotificationResolverFactory(List<NotificationResolver> resolverList) {
        for (NotificationResolver resolver : resolverList) {
            resolvers.put(resolver.getNotificationType(), resolver);
        }
    }

    public NotificationResolver getResolver(String notificationType) {
        return resolvers.get(notificationType);
    }
}
