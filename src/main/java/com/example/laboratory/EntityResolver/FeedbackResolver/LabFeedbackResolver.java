package com.example.laboratory.EntityResolver.FeedbackResolver;

import org.springframework.stereotype.Component;

@Component
public class LabFeedbackResolver implements FeedbackResolver {

    @Override
    public String getFeedbackType() {
        return "Lab";
    }

    @Override
    public Object resolve(Long feedbackId) {
        return "Resolved Lab feedback with ID: " + feedbackId;
    }
}
