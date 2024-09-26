package com.example.laboratory.Service;

import com.example.laboratory.EntityResolver.ProfileResolver.Factory.ProfileResolverFactory;
import com.example.laboratory.ExceptionHandler.ExceptionType;
import com.example.laboratory.ExceptionHandler.Factory.UserExceptionFactory;
import com.example.laboratory.Repository.UserRepository;
import com.example.laboratory.models.Users;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FCMService fcmService;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    private ProfileResolverFactory profileResolverFactory;
    public Users registerUser(Users user)
    {

        user.setEmail(user.getEmail().toLowerCase());
        logger.info("Received registration request for email: {}", user.getEmail());

//        logger.info("admin", userRepository.existsByIs_admin(user.getIs_admin()));
        if (userRepository.existsByEmail(user.getEmail())) {
            logger.warn("Email already exists: {}", user.getEmail());
            throw UserExceptionFactory.createException(
                    ExceptionType.USER_ALREADY_EXISTS,
                    "Email already exists: " + user.getEmail()
            );
        }

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            logger.warn("Phone number already exists: {}", user.getPhoneNumber());
            throw UserExceptionFactory.createException(
                    ExceptionType.USER_ALREADY_EXISTS,
                    "Phone number already exists: " + user.getPhoneNumber()
            );
        }


        // Saving user's credentials
        user.setPassword(passwordEncoder.encode(user.getPassword()));



        logger.debug("User object created: {}", user);


        logger.info("User's profile {}", user.getProfileType());
        // Create the appropriate profile based on the selected type
        Long profileId = profileResolverFactory.createProfile(user);

        logger.info(String.valueOf(profileId));
        // Set the profileId in Users entity
        user.setProfileId(profileId);

        // Save the user
        userRepository.save(user);
        logger.info("User registered successfully with ID: {}", user.getId());
        // Optionally associate the profile ID with the user if needed (in a polymorphic relationship)
        return user;  // Return user with the created profile
    }

    public void updateFCMToken(Long userId, String fcmToken) {
        logger.info("Updating FCM token for user ID: {}", userId);
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> UserExceptionFactory.createException(
                        ExceptionType.USER_NOT_FOUND,
                        "User not found"

                ));

        logger.info("Validating new FCM token for user ID: {}", userId);
        if (fcmService.isValidFCMToken(fcmToken)) {
            user.setFcmToken(fcmToken);
            userRepository.save(user);
            logger.info("FCM token updated successfully for user ID: {}", userId);

        } else {
            logger.error("Invalid FCM token provided for user ID: {}", userId);
            throw UserExceptionFactory.createException(
                    ExceptionType.INVALID_FCM_TOKEN,
                    "Invalid FCM token"
            );
        }
    }
}
