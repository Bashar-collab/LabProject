package com.example.laboratory.EntityResolver.FeedbackResolver;

import org.springframework.stereotype.Component;

@Component
public class DoctorFeedbackResolver implements FeedbackResolver{
    @Override
    public String getFeedbackType() {
        return "Doctor";
    }

    @Override
    public Object resolve(Long feedbackId) {
        return "Resolved Doctor feedback with ID: " + feedbackId;
    }
}
