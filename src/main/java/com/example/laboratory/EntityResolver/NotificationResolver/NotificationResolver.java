package com.example.laboratory.EntityResolver.NotificationResolver;

public interface NotificationResolver {
    String getNotificationType(); // Method to return the entity type
    Object resolve(Long NotificationId); // Method to resolve the entity by ID
}
