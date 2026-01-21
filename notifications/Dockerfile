# Dockerfile for Notifications Service
FROM openjdk:26-ea-21-jdk-slim
COPY ./target/*.jar notifications-service.jar
EXPOSE 8080
CMD ["java", "-jar", "notifications-service.jar"]
