# Dockerfile for Config Server
FROM openjdk:26-ea-21-jdk-slim
COPY ./target/*.jar config-server.jar
EXPOSE 8888
CMD ["java", "-jar", "config-server.jar"]