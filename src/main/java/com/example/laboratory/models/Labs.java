package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;

@Entity
public class Labs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @NotBlank
    private String lab_name;

    @NotBlank
    private String contact_number;

    @NotBlank
    @Column(columnDefinition = "CLOB")
    private String operating_hours;

    private Time deleted_at;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLab_name() {
        return lab_name;
    }

    public void setLab_name(String lab_name) {
        this.lab_name = lab_name;
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

    public Time getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Time deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
