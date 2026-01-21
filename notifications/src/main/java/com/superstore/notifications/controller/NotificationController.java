package com.superstore.notifications.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.superstore.notifications.dto.Notification;
import com.superstore.notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/notification-service")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody Notification notification) {
        System.out.print("Post mapping");
        notificationService.sendNotification(notification);
        return ResponseEntity.ok("Notification sent successfully.");
    }

    @GetMapping("/")
    public String getNotification(){
        return "Notification Service is working properly";
    }
}
