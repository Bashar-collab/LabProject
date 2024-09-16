package com.example.laboratory.EntityResolver.AddressResolver;

import com.example.laboratory.Repository.AddressRepository;
import com.example.laboratory.models.Addresses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
public class UserAddressResolver implements AddressResolver {

    @Override
    public String getAddressType() {
        return "User";
    }

    @Override
    public Object resolve(Addresses address) {
        return "Resolved User Address: " + address.getStreet();
    }

}
