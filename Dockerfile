# Dockerfile para el microservicio con H2 y Java 17
FROM openjdk:17-jdk-slim
COPY target/my-service-h2.jar my-service-h2.jar
ENTRYPOINT ["java", "-jar", "my-service-h2.jar"]
EXPOSE 8081
