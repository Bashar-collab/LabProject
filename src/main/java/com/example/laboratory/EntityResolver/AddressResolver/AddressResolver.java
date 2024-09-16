package com.example.laboratory.EntityResolver.AddressResolver;

import com.example.laboratory.models.Addresses;

public interface AddressResolver {
    String getAddressType(); // Method to return the entity type
    Object resolve(Addresses address); // Method to resolve the entity by address
}
