package com.example.laboratory.models.DTO;

public class TestCategoriesDTO {
    private final long id;
    private String category;

    public TestCategoriesDTO(long id, String category) {
        this.id = id;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
