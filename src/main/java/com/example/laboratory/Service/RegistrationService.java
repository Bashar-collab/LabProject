package com.example.laboratory.Service;

import com.example.laboratory.Repository.UserRepository;
import com.example.laboratory.Request.UserRegistrationRequest;
import com.example.laboratory.models.Users;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FCMService fcmService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Users registerUser(UserRegistrationRequest userRegistrationRequest)
    {

        logger.info("Received registration request for email: {}", userRegistrationRequest.getEmail());

        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
            logger.warn("Email already exists: {}", userRegistrationRequest.getEmail());
            throw new RuntimeException("Email already exists");
        }

        Users user = new Users();
        user.setUsername(userRegistrationRequest.getUsername());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        user.setFcm_token(userRegistrationRequest.getFcm_token());

        // Set isAdmin and isVerified field (defaults to false)
        user.setVerified(false);
        user.setIs_admin(false);

        logger.debug("User object created: {}", user);
        // Validate FCM token
        if (user.getFcm_token() != null && !fcmService.isValidFCMToken(user.getFcm_token())) {
            logger.error("Invalid FCM token provided for user: {}", userRegistrationRequest.getEmail());
            throw new RuntimeException("Invalid FCM token");
        }
//
//        try{
//
//        }
        return userRepository.save(user);
    }

    public void updateFCMToken(Long userId, String fcmToken) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (fcmService.isValidFCMToken(fcmToken)) {
            user.setFcm_token(fcmToken);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Invalid FCM token");
        }
    }
}
