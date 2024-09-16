package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class TestCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String category;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
