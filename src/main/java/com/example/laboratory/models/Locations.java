package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Locations {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String governorate_en;
    @NotBlank
    private String governorate_ar;

    @NotBlank
    private String district_en;

    @NotBlank
    private String district_ar;

    @NotBlank
    private String City_en;

    @NotBlank
    private String City_ar;

    @Column(columnDefinition = "NUMBER")
    private Long latitude_y;

    @Column(columnDefinition = "NUMBER")
    private Long longitude_y;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGovernorate_en() {
        return governorate_en;
    }

    public void setGovernorate_en(String governorate_en) {
        this.governorate_en = governorate_en;
    }

    public String getGovernorate_ar() {
        return governorate_ar;
    }

    public void setGovernorate_ar(String governorate_ar) {
        this.governorate_ar = governorate_ar;
    }

    public String getDistrict_en() {
        return district_en;
    }

    public void setDistrict_en(String district_en) {
        this.district_en = district_en;
    }

    public String getDistrict_ar() {
        return district_ar;
    }

    public void setDistrict_ar(String district_ar) {
        this.district_ar = district_ar;
    }

    public String getCity_en() {
        return City_en;
    }

    public void setCity_en(String city_en) {
        City_en = city_en;
    }

    public String getCity_ar() {
        return City_ar;
    }

    public void setCity_ar(String city_ar) {
        City_ar = city_ar;
    }

    public Long getLatitude_y() {
        return latitude_y;
    }

    public void setLatitude_y(Long latitude_y) {
        this.latitude_y = latitude_y;
    }

    public Long getLongitude_y() {
        return longitude_y;
    }

    public void setLongitude_y(Long longitude_y) {
        this.longitude_y = longitude_y;
    }
}
