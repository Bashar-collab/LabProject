package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

@Entity
public class LabTests {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Tests tests;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Labs labs;

    @NotBlank
    private double cost;

    private Timestamp deleted_at;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tests getTests() {
        return tests;
    }

    public void setTests(Tests tests) {
        this.tests = tests;
    }

    public Labs getLabs() {
        return labs;
    }

    public void setLabs(Labs labs) {
        this.labs = labs;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }
}
