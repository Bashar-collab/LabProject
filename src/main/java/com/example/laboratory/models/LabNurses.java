package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

@Entity
public class LabNurses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Labs labs;

    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private Nurses nurses;

    @NotBlank
    @Column(columnDefinition = "CLOB")
    private String working_hours;

    @NotBlank
    @Column(columnDefinition = "CLOB")
    private String contract_details;

    private Timestamp deleted_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Labs getLabs() {
        return labs;
    }

    public void setLabs(Labs labs) {
        this.labs = labs;
    }

    public Nurses getNurses() {
        return nurses;
    }

    public void setNurses(Nurses nurses) {
        this.nurses = nurses;
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
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
