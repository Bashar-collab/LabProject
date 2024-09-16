package com.example.laboratory.Repository;

import com.example.laboratory.models.Addresses;
import oracle.net.jdbc.TNSAddress.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Addresses, Long> {
    @Override
    Optional<Addresses> findById(Long aLong);
}
