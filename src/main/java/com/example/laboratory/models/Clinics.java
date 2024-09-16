package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

@Entity
public class Clinics {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctors doctors;

    @NotBlank
    private String name;

    @NotBlank
    private String contact_number;

    @NotBlank
    @Column(columnDefinition = "CLOB")
    private String operating_hours;

    private Timestamp deleted_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getOperating_hours() {
        return operating_hours;
    }

    public void setOperating_hours(String operating_hours) {
        this.operating_hours = operating_hours;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }
}
