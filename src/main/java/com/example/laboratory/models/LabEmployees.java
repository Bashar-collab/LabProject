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
    private String contract_details;

    private Timestamp deleted_at;

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

    public String getContract_details() {
        return contract_details;
    }

    public void setContract_details(String contract_details) {
        this.contract_details = contract_details;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }
}
