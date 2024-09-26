package com.example.laboratory.Service;

import com.example.laboratory.Repository.UserRepository;
import com.example.laboratory.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public void updateFcmToken(Long userId, String fcmToken) {
        // Fetch user from the database
        Optional<Users> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setFcmToken(fcmToken);  // Set the new FCM token
            userRepository.save(user);   // Save the user back to the database
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
