package com.superstore.notifications.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class NotificationsConfiguration {
    @Bean
    public SnsClient amazonSNS() {
        return SnsClient.create();
    }
}
