package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class LabDeliveries {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Labs labs;

    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private Nurses nurses;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patients;

    @NotBlank
    private String sample_type;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    private String estimated_time;

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

    public Patients getPatients() {
        return patients;
    }

    public void setPatients(Patients patients) {
        this.patients = patients;
    }

    public String getSample_type() {
        return sample_type;
    }

    public void setSample_type(String sample_type) {
        this.sample_type = sample_type;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(String estimated_time) {
        this.estimated_time = estimated_time;
    }
}
