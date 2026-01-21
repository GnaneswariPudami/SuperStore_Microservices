# notifications-service Dockerfile
FROM maven:3.8.5-openjdk-21 AS builder
WORKDIR /Users/j84527082/SuperStore_Microservices/notifications
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:26-ea-21-jdk-slim
WORKDIR /Users/j84527082/SuperStore_Microservices/notifications
COPY --from=builder /Users/j84527082/SuperStore_Microservices/notifications/target/*.jar notifications-service.jar
EXPOSE 8080
CMD ["java", "-jar", "notifications-service.jar"]

# product-service Dockerfile
FROM maven:3.8.5-openjdk-21 AS builder
WORKDIR /Users/j84527082/SuperStore_Microservices/product
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:26-ea-21-jdk-slim
WORKDIR /Users/j84527082/SuperStore_Microservices/product
COPY --from=builder /Users/j84527082/SuperStore_Microservices/product/target/*.jar product-service.jar
EXPOSE 8081
CMD ["java", "-jar", "product-service.jar"]

# useraccount-service Dockerfile
FROM maven:3.8.5-openjdk-21 AS builder
WORKDIR /Users/j84527082/SuperStore_Microservices/useraccount
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:26-ea-21-jdk-slim
WORKDIR /Users/j84527082/SuperStore_Microservices/useraccount
COPY --from=builder /Users/j84527082/SuperStore_Microservices/useraccount/target/*.jar useraccount-service.jar
EXPOSE 8082
CMD ["java", "-jar", "useraccount-service.jar"]

# order-service Dockerfile
FROM maven:3.8.5-openjdk-21 AS builder
WORKDIR /Users/j84527082/SuperStore_Microservices/order
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:26-ea-21-jdk-slim
WORKDIR /Users/j84527082/SuperStore_Microservices/order
COPY --from=builder /Users/j84527082/SuperStore_Microservices/order/target/*.jar order-service.jar
EXPOSE 8083
CMD ["java", "-jar", "order-service.jar"]

