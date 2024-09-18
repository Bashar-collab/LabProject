package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "CLOB")
    private String medicalHistory;

    @Column(columnDefinition = "CLOB")
    private String allergies;

    @Column(columnDefinition = "CLOB")
    private String currentMedications;

    private String bloodType;

    private double height;

    private double weight;

    private String emergencyContact;

    private String emergencyContactRelation;

    private Timestamp deletedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    /*
    @JoinTable annotation used to define the join table
    that maps many-to-many relationship between user and role entities
     */
    @JoinTable( name = "doctor_patients",
            joinColumns = @JoinColumn(name = "patients_id"),
            inverseJoinColumns = @JoinColumn(name = "doctors_id"))
    /*
     field that holds the collection of associated Role entities
     Set<Role> to ensure that there are no duplicate roles for a user
     HashSet is to ensure that the collection is ready to be used and avoides NullPointerException
     */
    private Set<Doctors> doctors;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedical_history() {
        return medicalHistory;
    }

    public void setMedical_history(String medical_history) {
        this.medicalHistory = medical_history;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getCurrent_medications() {
        return currentMedications;
    }

    public void setCurrent_medications(String current_medications) {
        this.currentMedications = current_medications;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Set<Doctors> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctors> doctors) {
        this.doctors = doctors;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCurrentMedications() {
        return currentMedications;
    }

    public void setCurrentMedications(String currentMedications) {
        this.currentMedications = currentMedications;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactRelation() {
        return emergencyContactRelation;
    }

    public void setEmergencyContactRelation(String emergencyContactRelation) {
        this.emergencyContactRelation = emergencyContactRelation;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }
}
