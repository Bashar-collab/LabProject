package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
public class TestResults {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_request_id")
    private PatientsRequests patientsRequests;

    @NotBlank
    private String test_type;

    @NotBlank
    @Column(columnDefinition = "CLOB")
    private String result;

    @NotBlank
    private Date date_of_test;

    @Column(columnDefinition = "CLOB")
    private String notes;

    @Enumerated(EnumType.STRING)
    private TestResultStatus testResultStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientsRequests getPatientsRequests() {
        return patientsRequests;
    }

    public void setPatientsRequests(PatientsRequests patientsRequests) {
        this.patientsRequests = patientsRequests;
    }

    public String getTest_type() {
        return test_type;
    }

    public void setTest_type(String test_type) {
        this.test_type = test_type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getDate_of_test() {
        return date_of_test;
    }

    public void setDate_of_test(Date date_of_test) {
        this.date_of_test = date_of_test;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public TestResultStatus getTestResultStatus() {
        return testResultStatus;
    }

    public void setTestResultStatus(TestResultStatus testResultStatus) {
        this.testResultStatus = testResultStatus;
    }
}
