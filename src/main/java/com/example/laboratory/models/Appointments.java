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
    private Date appointmentDate;

    @NotBlank
    private Time appointmentTime;

    @Column(columnDefinition = "CLOB")
    private String requiredTests;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;

    @Column(columnDefinition = "CLOB")
    private String prescription;

    @Column(columnDefinition = "CLOB")
    private String notes;

    private Timestamp deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }

    public Patients getPatients() {
        return patients;
    }

    public void setPatients(Patients patients) {
        this.patients = patients;
    }

    public Clinics getClinics() {
        return clinics;
    }

    public void setClinics(Clinics clinics) {
        this.clinics = clinics;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getRequiredTests() {
        return requiredTests;
    }

    public void setRequiredTests(String requiredTests) {
        this.requiredTests = requiredTests;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }
}
