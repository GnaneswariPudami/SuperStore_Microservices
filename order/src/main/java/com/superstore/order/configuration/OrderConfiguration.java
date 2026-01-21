package com.superstore.order.configuration;

import com.superstore.order.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfiguration {
    @Bean
    public OrderService productBean(){
        return new OrderService();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
