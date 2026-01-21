package com.superstore.product.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superstore.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class ProductConfiguration {
    @Bean
    public ProductService productBean(){
        return new ProductService();
    }

    @Bean
    public S3Client s3Client() {
        return S3Client.create();
    }

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.create();
    }

    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
