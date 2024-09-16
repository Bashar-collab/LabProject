package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class PatientsRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patients;

    @ManyToOne
    @JoinColumn(name = "labs_id")
    private Labs labs;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointments appointments;

    @NotBlank
    private double cost;

    @Enumerated(EnumType.STRING)
    private RequestsStatus requestsStatus;

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

    public Labs getLabs() {
        return labs;
    }

    public void setLabs(Labs labs) {
        this.labs = labs;
    }

    public Appointments getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointments appointments) {
        this.appointments = appointments;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public RequestsStatus getRequestsStatus() {
        return requestsStatus;
    }

    public void setRequestsStatus(RequestsStatus requestsStatus) {
        this.requestsStatus = requestsStatus;
    }
}
