package com.example.laboratory.EntityResolver.FeedbackResolver;

public interface FeedbackResolver {
    String getFeedbackType(); // Method to return the entity type
    Object resolve(Long feedbackId); // Method to resolve the entity by ID
}
