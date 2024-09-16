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
    private String special_skills;

    private Date deleted_at;

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

    public String getSpecial_skills() {
        return special_skills;
    }

    public void setSpecial_skills(String special_skills) {
        this.special_skills = special_skills;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
}
