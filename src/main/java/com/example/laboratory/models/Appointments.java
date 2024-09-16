package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctors doctors;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patients;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinics clinics;

    @NotBlank
    private Date appointment_date;

    @NotBlank
    private Time appointment_time;

    @Column(columnDefinition = "CLOB")
    private String required_tests;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;

    @Column(columnDefinition = "CLOB")
    private String prescription;

    @Column(columnDefinition = "CLOB")
    private String notes;

    private Timestamp deleted_at;
}
