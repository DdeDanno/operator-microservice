# Dockerfile para el microservicio con H2 y Java 17
FROM openjdk:17-jdk-slim
COPY target/operator-microservice-0.0.1-SNAPSHOT.jar my-service-h2.jar
ENTRYPOINT ["java", "-jar", "my-service-h2.jar"]
EXPOSE 8081
