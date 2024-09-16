package com.example.laboratory.EntityResolver.FeedbackResolver;

import org.springframework.stereotype.Component;

@Component
public class LabDeliveryFeedbackResolver implements FeedbackResolver{
    @Override
    public String getFeedbackType() {
        return "Lab";
    }

    @Override
    public Object resolve(Long feedbackId) {
        return "Resolved Lab Delivery feedback with ID: " + feedbackId;
    }
}
