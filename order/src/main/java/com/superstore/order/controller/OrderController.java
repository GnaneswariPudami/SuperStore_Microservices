package com.superstore.order.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.superstore.order.model.NotificationRequest;
import com.superstore.order.model.Order;
import com.superstore.order.service.NotificationServiceClient;
import com.superstore.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order-service")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Value("NOTIFICATION_SERVICE_URL")
    private String notificationServiceUrl = null;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationServiceClient notificationServiceClient;

    private NotificationRequest notificationRequest;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        notificationRequest.setType("Email");
        notificationRequest.setSubject("Order Created Successfully");
        notificationRequest.setMessage("Below Order has been created Successfully : "+ order);
        notificationRequest.setDestination("gnaneswari.pudami@gmail.com");
        sendNotificationEmail(notificationRequest);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    private void sendNotificationEmail(NotificationRequest notificationRequest) {
        try {
            notificationServiceClient.sendNotification(notificationRequest, notificationServiceUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
