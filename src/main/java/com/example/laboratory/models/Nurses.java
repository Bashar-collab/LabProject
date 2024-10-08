package com.example.laboratory.models;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.util.Date;

@Entity
public class Nurses {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "CLOB")
    private String certifications;


    @Column(columnDefinition = "CLOB")
    private String specialSkills;

    private Date deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public String getSpecialSkills() {
        return specialSkills;
    }

    public void setSpecialSkills(String specialSkills) {
        this.specialSkills = specialSkills;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
