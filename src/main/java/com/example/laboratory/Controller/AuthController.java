package com.example.laboratory.Controller;

import com.example.laboratory.Config.SecurityConfig.CustomUserDetails;
import com.example.laboratory.ExceptionHandler.InvalidFCMTokenException;
import com.example.laboratory.ExceptionHandler.UserAlreadyExistsException;
import com.example.laboratory.JWT.JwtTokenProvider;
import com.example.laboratory.Request.LoginRequest;
import com.example.laboratory.Response.AuthResponse;
import com.example.laboratory.Response.MessageResponse;
import com.example.laboratory.Response.RegistrationResponse;
import com.example.laboratory.Service.CustomUserDetailsService;
import com.example.laboratory.Service.FCMService;
import com.example.laboratory.Service.LoginService;
import com.example.laboratory.Service.RegistrationService;
import com.example.laboratory.models.Users;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;


@CrossOrigin(origins = "*", maxAge = 3600) // allows for a specific domains to make request for a limited time
@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final RegistrationService registrationService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtUtils;

    private final FCMService fcmService;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtTokenProvider jwtTokenProvider;

    private Users users;

    @Autowired
    private LoginService loginService;

    public AuthController(RegistrationService registrationService, AuthenticationManager authenticationManager, JwtTokenProvider jwtUtils, FCMService fcmService, CustomUserDetailsService customUserDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.registrationService = registrationService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.fcmService = fcmService;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Users user) {
        logger.debug("Attempting to register: {}", user);
        Users registeredUser;
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

    @PostMapping("/login")
    public ResponseEntity<?> loginRequest(@RequestBody LoginRequest loginRequest) {
        logger.info("Authenticating user's info");
        logger.info("Phone number: {}", loginRequest.getPhoneNumber());
        Authentication authentication;
        try {
            // Authenticate the user
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getPhoneNumber(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Incorrect password, please try again!"));  // 400 Bad Request
        } catch (InternalAuthenticationServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Phone number not registered, please try again!"));  // 400 Bad Request
        } catch (Exception e)
        {
            logger.info("Error while logging in " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error while logging in Please try again!"));
        }
        // After successful authentication, set the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Retrieve the authenticated UserDetails from SecurityContext
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Users user = ((CustomUserDetails) userDetails).getUsers();

        // Save FCM token in the database
        if (loginRequest.getFcmToken() != null && !loginRequest.getFcmToken().isEmpty()) {
            loginService.updateFcmToken(user.getId(), loginRequest.getFcmToken());  // Update FCM token in DB
        }
        // Load user's details
//        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getPhoneNumber());
        logger.info("User's Details name {}", userDetails.getUsername());

        // Generate Access and Refresh Tokens
        logger.info("User's phone number to generate token is {}", loginRequest.getPhoneNumber());
        String accessToken = jwtTokenProvider.generateAccessToken(loginRequest.getPhoneNumber());
//        String refreshToken = jwtTokenProvider.generateRefreshToken(loginRequest.getPhoneNumber());

        logger.info("User's authorizes: {}", userDetails.getAuthorities());

        // Return the token in the header

        // Return tokens in response
//        Map<String, String> tokens = new HashMap<>();
//        tokens.put("accessToken", accessToken);
//        tokens.put("refreshToken", refreshToken);
//        logger.info("Token is generated successfully " + accessToken);
        return ok()
                .body(new AuthResponse(accessToken,"Logged in Successfully!"));
    }

    @PostMapping("/api/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody Map<String, String> request) {
        String accessToken = request.get("accessToken");

        // Validate the refresh token (check expiration, signature, etc.)
        if (!jwtTokenProvider.validateToken(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Invalid or expired refresh token."));
        }
        // Get the phone number (subject) from the refresh token
        String phoneNumber = jwtTokenProvider.extractPhoneNumber(accessToken);

        // Check if phone number is null or empty
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Invalid refresh token."));
        }
        // Generate a new access token
        String refreshToken = jwtTokenProvider.generateRefreshToken(phoneNumber);

//        Map<String, String> response  = new HashMap<>();
//        response.put("refreshToken", refreshToken);

        return ResponseEntity.ok()
                .body(new MessageResponse(refreshToken));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        // Retrieve the JWT token from the Authorization header
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
//            logger.info("logout token: " + token);
            // Optional: Add logic to invalidate the token (blacklisting or setting expiration)
            jwtTokenProvider.expireToken(token);
            // Clear SecurityContext (logs the user out)
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok(new MessageResponse("User logged out successfully!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("No token found!"));
    }
}