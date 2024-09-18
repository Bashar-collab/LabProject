package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import javax.annotation.processing.Generated;
import java.sql.Timestamp;

@Entity
public class LabEmployees {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // Many-to-One relationship with lab
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_id")
    private Labs lab;

    @NotNull
    @Column(columnDefinition = "CLOB")
    private String contractDetails;

    private Timestamp deletedAt;

    public LabEmployees() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Labs getLab() {
        return lab;
    }

    public void setLab(Labs lab) {
        this.lab = lab;
    }

    public String getContractDetails() {
        return contractDetails;
    }

    public void setContractDetails(String contractDetails) {
        this.contractDetails = contractDetails;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }
}
