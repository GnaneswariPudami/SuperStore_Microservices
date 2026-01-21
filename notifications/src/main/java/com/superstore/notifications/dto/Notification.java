package com.superstore.notifications.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification {
    private String message;
    private String subject;
    private String destination; // Email, phone number, etc.
    private String type; // Email, SMS, etc.
}
