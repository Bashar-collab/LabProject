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
    private String testType;

    @NotBlank
    @Column(columnDefinition = "CLOB")
    private String result;

    @NotBlank
    private Date dateOfTest;

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

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getDateOfTest() {
        return dateOfTest;
    }

    public void setDateOfTest(Date dateOfTest) {
        this.dateOfTest = dateOfTest;
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
