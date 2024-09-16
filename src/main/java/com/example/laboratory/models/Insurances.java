package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Insurances {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patients patients;

    @NotBlank
    private String provider_name;

    @NotBlank
    private String insurance_number;

    @NotBlank
    @Column(columnDefinition = "CLOB")
    private String coverage_details;

    @NotBlank
    private String contact_info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patients getPatients() {
        return patients;
    }

    public void setPatients(Patients patients) {
        this.patients = patients;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getInsurance_number() {
        return insurance_number;
    }

    public void setInsurance_number(String insurance_number) {
        this.insurance_number = insurance_number;
    }

    public String getCoverage_details() {
        return coverage_details;
    }

    public void setCoverage_details(String coverage_details) {
        this.coverage_details = coverage_details;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }
}
