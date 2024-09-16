package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Locations locations;

    @NotBlank
    @Column(name = "address_id")
    private Long addressId;

    @NotBlank
    @Column(name = "address_type")
    private String addressType;

    private String street;

    private String building_number;

    private String apartment_number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Locations getLocations() {
        return locations;
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding_number() {
        return building_number;
    }

    public void setBuilding_number(String building_number) {
        this.building_number = building_number;
    }

    public String getApartment_number() {
        return apartment_number;
    }

    public void setApartment_number(String apartment_number) {
        this.apartment_number = apartment_number;
    }
}
