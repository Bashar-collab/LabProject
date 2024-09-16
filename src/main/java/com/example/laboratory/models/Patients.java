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
    private String medical_history;

    @Column(columnDefinition = "CLOB")
    private String allergies;

    @Column(columnDefinition = "CLOB")
    private String current_medications;

    @NotBlank
    private String blood_type;

    @NotBlank
    private double height;

    @NotBlank
    private double weight;

    @NotBlank
    private String emergency_contact;

    @NotBlank
    private String emergency_contact_relation;

    private Timestamp deleted_at;

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
        return medical_history;
    }

    public void setMedical_history(String medical_history) {
        this.medical_history = medical_history;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getCurrent_medications() {
        return current_medications;
    }

    public void setCurrent_medications(String current_medications) {
        this.current_medications = current_medications;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
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

    public String getEmergency_contact() {
        return emergency_contact;
    }

    public void setEmergency_contact(String emergency_contact) {
        this.emergency_contact = emergency_contact;
    }

    public String getEmergency_contact_relation() {
        return emergency_contact_relation;
    }

    public void setEmergency_contact_relation(String emergency_contact_relation) {
        this.emergency_contact_relation = emergency_contact_relation;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }
}
