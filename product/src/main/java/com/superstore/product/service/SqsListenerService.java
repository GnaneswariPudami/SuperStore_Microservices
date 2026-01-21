package com.superstore.product.service;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class SqsListenerService {

    private final SqsClient sqsClient;
    private final ProductIngestionService productIngestionService;
    private final ObjectMapper objectMapper;

    @Value("${aws.sqs.queue.url}")
    private String queueUrl;

    @Autowired
    public SqsListenerService(SqsClient sqsClient, ProductIngestionService productIngestionService, ObjectMapper objectMapper) {
        this.sqsClient = sqsClient;
        this.productIngestionService = productIngestionService;
        this.objectMapper = objectMapper;
    }

    //@Scheduled(fixedDelay = 10000) // Poll every 10 seconds

    public void listenForSqsMessages() throws IOException {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .build();

        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();

        for (Message message : messages) {
            JsonNode jsonNode = objectMapper.readTree(message.body());
            String s3Key = jsonNode.get("Records").get(0).get("s3").get("object").get("key").asText();
            productIngestionService.processS3File(s3Key);
            sqsClient.deleteMessage(builder -> builder.queueUrl(queueUrl).receiptHandle(message.receiptHandle()));
        }
    }
}