package com.superstore.notifications.service;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import com.superstore.notifications.dto.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private final SnsClient snsClient;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Value("${aws.sns.topic.arn}")
    private String topicArn;

    @Autowired
    public NotificationService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public void sendNotification(Notification notification) {
        try {
            PublishRequest request = PublishRequest.builder()
                    .topicArn(topicArn)
                    .message(notification.getMessage())
                    .subject(notification.getSubject())
                    .build();
            PublishResponse result = snsClient.publish(request);
            logger.info(result.messageId() + " Message sent. Status was " + result.sdkHttpResponse().statusCode());
            snsClient.close();
        } catch (Exception e) {
            logger.error("Failed to send SNS message: {}", e.getMessage());
        }
    }
}
