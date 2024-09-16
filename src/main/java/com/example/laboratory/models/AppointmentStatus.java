package com.example.laboratory.models;

public enum AppointmentStatus {
    SCHEDULED,           // The appointment is scheduled
    PENDING_CONFIRMATION, // The appointment is pending confirmation
    CONFIRMED,           // The appointment is confirmed
    COMPLETED,           // The appointment is completed
    RESCHEDULED,         // The appointment has been rescheduled
    CANCELLED,           // The appointment is cancelled
    NO_SHOW,             // The person did not show up for the appointment
    POSTPONED,           // The appointment is postponed
    FAILED               // The appointment could not proceed
}

