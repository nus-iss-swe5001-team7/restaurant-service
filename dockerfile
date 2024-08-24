FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/restaurant-service.jar /app/restaurant-service.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app/restaurant-service.jar"]
