# Dockerfile for User Account Service
FROM openjdk:26-ea-21-jdk-slim
WORKDIR /Users/j84527082/SuperStore_Microservices/useraccount
COPY --from=builder /Users/j84527082/SuperStore_Microservices/useraccount/target/*.jar useraccount-service.jar
EXPOSE 8082
CMD ["java", "-jar", "useraccount-service.jar"]