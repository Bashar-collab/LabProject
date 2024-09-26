package com.example.laboratory.Repository;

import com.example.laboratory.models.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @Override
    Optional<Users> findById(Long aLong);
    // some methods

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Users> findByUsername(String username);

    Optional<Users> findByPhoneNumber(String phoneNumber);
//    boolean existsByIs_admin(boolean admin);
}