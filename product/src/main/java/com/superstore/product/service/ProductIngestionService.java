package com.superstore.product.service;

import com.superstore.product.entity.Product;
import com.superstore.product.repository.ProductRepository;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class ProductIngestionService {

    private final S3Client s3Client;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Autowired
    public ProductIngestionService(S3Client s3Client, ProductRepository productRepository, ObjectMapper objectMapper) {
        this.s3Client = s3Client;
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    public void processS3File(String s3Key) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
        byte[] data = objectBytes.asByteArray();

        List<Product> products = objectMapper.readValue(data, new TypeReference<List<Product>>() {});
        productRepository.saveAll(products);
    }
}
