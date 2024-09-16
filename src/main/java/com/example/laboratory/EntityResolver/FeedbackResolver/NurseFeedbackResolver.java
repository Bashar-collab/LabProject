package com.example.laboratory.EntityResolver.FeedbackResolver;

import org.springframework.stereotype.Component;

@Component
public class NurseFeedbackResolver implements FeedbackResolver{

    @Override
    public String getFeedbackType() {
        return "Nurse";
    }

    @Override
    public Object resolve(Long feedbackId) {
        return "Resolved Nurse feedback with ID: " + feedbackId;
    }
}
