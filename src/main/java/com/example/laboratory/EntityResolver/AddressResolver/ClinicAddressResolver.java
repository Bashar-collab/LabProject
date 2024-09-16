package com.example.laboratory.EntityResolver.AddressResolver;

import com.example.laboratory.models.Addresses;
import org.springframework.stereotype.Component;

@Component
public class ClinicAddressResolver implements AddressResolver{


    @Override
    public String getAddressType() {
        return "Clinic";
    }

    @Override
    public Object resolve(Addresses address) {
        return "Resolved Clinic Address: " + address.getStreet();
    }
}
