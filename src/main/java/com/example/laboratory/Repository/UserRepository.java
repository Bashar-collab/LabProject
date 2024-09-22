package com.example.laboratory.Repository;

import com.example.laboratory.models.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    @Override
    Optional<Users> findById(Long aLong);
    // some methods

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phone_number);

//    boolean existsByIs_admin(boolean admin);
}