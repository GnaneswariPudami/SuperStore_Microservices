package com.superstore.order.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationRequest {
    private String message;
    private String subject;
    private String destination;
    private String type;
}
