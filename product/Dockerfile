# Dockerfile for Product Service
FROM openjdk:26-ea-21-jdk-slim
WORKDIR /Users/j84527082/SuperStore_Microservices/product
COPY --from=builder /Users/j84527082/SuperStore_Microservices/product/target/*.jar product-service.jar
EXPOSE 8081
CMD ["java", "-jar", "product-service.jar"]
