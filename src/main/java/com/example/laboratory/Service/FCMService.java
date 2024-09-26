package com.example.laboratory.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;


@Service
public class FCMService {
    private static final Logger logger = LoggerFactory.getLogger(FCMService.class);

    public void sendNotification(String fcmToken, String title, String body) {
        logger.info("Sending notification");
        try {
            Message message = Message.builder()
                    .putData("title", title)
                    .putData("body", body)
                    .setToken(fcmToken)
                    .build();


            // Send the message using Firebase
            String response = FirebaseMessaging.getInstance().send(message);
            logger.info("Notification sent: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValidFCMToken(String fcmToken) {
        try {
            // Create a test message to the FCM token
            Message message = Message.builder()
                    .setToken(fcmToken)
                    .putData("test", "ping")
                    .build();
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            if (e.getErrorCode().equals("messaging/registration-token-not-registered") ||
                    e.getErrorCode().equals("messaging/invalid-registration-token")) {
                // Token is no longer valid, take appropriate action
                System.out.println("FCM token is invalid or expired.");
                // Remove the token from the database, log the error, etc.
                return false;
            }
        }
        return true;
    }
}
