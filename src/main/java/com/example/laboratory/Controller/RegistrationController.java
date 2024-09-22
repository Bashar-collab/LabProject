package com.example.laboratory.Controller;

import com.example.laboratory.ExceptionHandler.InvalidFCMTokenException;
import com.example.laboratory.ExceptionHandler.UserAlreadyExistsException;
import com.example.laboratory.Request.UserRegistrationRequest;
import com.example.laboratory.Response.MessageResponse;
import com.example.laboratory.Response.RegistrationResponse;
import com.example.laboratory.Service.FCMService;
import com.example.laboratory.Service.RegistrationService;
import com.example.laboratory.Service.TestCategoryService;
import com.example.laboratory.models.Users;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(TestCategoryService.class);

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private FCMService fcmService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Users user) {
        logger.debug("Attempting to register: {}", user);
        Users registeredUser = null;
        try {
            registeredUser = registrationService.registerUser(user);
            RegistrationResponse response = new RegistrationResponse(registeredUser.getId(), "User registered successfully");

            logger.info("User registered successfully with ID: {}", registeredUser.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);  // Use 201 Created

        } catch (UserAlreadyExistsException e) {
            logger.warn("User registration failed. Reason: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("Phone number or Email already exists"));  // 409 Conflict

        } catch (InvalidFCMTokenException e) {
            logger.warn("Invalid FCM token. Reason: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error while registering, please try again!"));  // 400 Bad Request

        } catch (Exception e) {
            logger.error("Unexpected error occurred during user registration", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse(e.getMessage()));  // 500 Internal Server Error
        }
    }
}