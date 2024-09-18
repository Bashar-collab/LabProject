package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patients;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Labs labs;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethods paymentMethods;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private Insurances insurances;

    @NotBlank
    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @NotBlank
    private String transactionId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public Insurances getInsurances() {
        return insurances;
    }

    public void setInsurances(Insurances insurances) {
        this.insurances = insurances;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransaction_id(String transaction_id) {
        this.transactionId = transactionId;
    }
}
