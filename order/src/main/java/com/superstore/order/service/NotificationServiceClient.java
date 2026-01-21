package com.superstore.order.service;

import com.superstore.order.model.NotificationRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceClient {
    private final RestTemplate restTemplate;

    public NotificationServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> sendNotification(NotificationRequest notificationRequest, String notificationServiceUrl) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NotificationRequest> requestEntity = new HttpEntity<>(notificationRequest, headers);

        try {
            return restTemplate.exchange(
                    notificationServiceUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class); // Or your notification service response class
        } catch (Exception e) {
            // Handle exceptions (e.g., connection issues, service unavailable)
            System.err.println("Error sending notification: " + e.getMessage());
            return ResponseEntity.status(500).body("Notification service unavailable or error.");
        }
    }
}
