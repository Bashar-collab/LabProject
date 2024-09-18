package com.example.laboratory.Controller;

import com.example.laboratory.Request.UserRegistrationRequest;
import com.example.laboratory.Service.FCMService;
import com.example.laboratory.Service.RegistrationService;
import com.example.laboratory.models.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private FCMService fcmService;

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        Users registeredUser = registrationService.registerUser(userRegistrationRequest);
        RegistrationResponse response = new RegistrationResponse(registeredUser.getId(), "User registered successfully");
        return ResponseEntity.ok(response);
    }
}