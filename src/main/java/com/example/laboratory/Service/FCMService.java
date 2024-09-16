package com.example.laboratory.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class FcmNotificationService {

    public void sendNotification(String fcmToken, String title, String body)
    {
        try {
            Message message = Message.builder()
                    .putData("title", title)
                    .putData("body", body)
                    .setToken(fcmToken)
                    .build();


        // Send the message using Firebase
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Notification sent: " + response);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public boolean isValidFCMToken(String fcmToken) {
        try {
            FirebaseMessaging.getInstance().subscribeToTopic(Collections.singletonList(fcmToken), "test_topic");
            FirebaseMessaging.getInstance().unsubscribeFromTopic(Collections.singletonList(fcmToken), "test_topic");
            return true;
        } catch (FirebaseMessagingException e) {
            return false;
        }
    }
}
