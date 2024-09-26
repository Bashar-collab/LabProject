package com.example.laboratory.Service;

import com.example.laboratory.Config.SecurityConfig.CustomUserDetails;
import com.example.laboratory.ExceptionHandler.UserNotFoundException;
import com.example.laboratory.Repository.UserRepository;
import com.example.laboratory.models.Users;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        logger.info("Loading user: {}", phoneNumber);
        Users user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        logger.info("User found: {}", user.getUsername());
        return new CustomUserDetails(user);
    }
}
