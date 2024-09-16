package com.example.laboratory.EntityResolver.AddressResolver;

import com.example.laboratory.models.Addresses;
import org.springframework.stereotype.Component;

@Component
public class LabAddressResolver implements AddressResolver{

    @Override
    public String getAddressType() {
        return "Lab";
    }

    @Override
    public Object resolve(Addresses address) {
        return "Resolved Lab Address: " + address.getStreet();
    }
}
