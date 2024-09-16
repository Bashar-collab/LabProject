package com.example.laboratory.EntityResolver.AddressResolver.Factory;

import com.example.laboratory.EntityResolver.AddressResolver.AddressResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressResolverFactory {
    private final Map<String, AddressResolver> resolvers = new HashMap<>();

    @Autowired
    public AddressResolverFactory(List<AddressResolver> resolverList) {
        for (AddressResolver resolver : resolverList) {
            resolvers.put(resolver.getAddressType(), resolver);
        }
    }

    public AddressResolver getResolver(String addressType) {
        return resolvers.get(addressType);
    }
}
