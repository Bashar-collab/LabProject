package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;
//import jakarta.persistence.Table;

@Entity
public class Otps {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String phone;

    @NotBlank
    private String otp;

    private Timestamp expires_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Timestamp getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(Timestamp expires_at) {
        this.expires_at = expires_at;
    }
}
