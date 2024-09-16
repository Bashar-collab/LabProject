package com.example.laboratory.models;

public enum DeliveryStatus {
    PENDING,            // The delivery is pending
    PROCESSING,         // The delivery is being processed
    IN_TRANSIT,         // The delivery is in transit
    DELIVERED,          // The delivery has been delivered
    CANCELLED,          // The delivery has been cancelled
    FAILED              // The delivery attempt failed
}

