# Dockerfile for Order Service
FROM openjdk:26-ea-21-jdk-slim
WORKDIR /Users/j84527082/SuperStore_Microservices/order
COPY --from=builder /Users/j84527082/SuperStore_Microservices/order/target/*.jar order-service.jar
EXPOSE 8083
CMD ["java", "-jar", "order-service.jar"]

