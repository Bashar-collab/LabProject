package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_category_id")
    private TestCategories testCategories;

    @NotBlank
    private String testName;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestCategories getTestCategories() {
        return testCategories;
    }

    public void setTestCategories(TestCategories testCategories) {
        this.testCategories = testCategories;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String test_name) {
        this.testName = test_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
