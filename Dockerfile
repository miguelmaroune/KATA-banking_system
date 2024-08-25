FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/bankingSystem-0.0.1-SNAPSHOT.jar bankingSystem-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "bankingSystem-0.0.1-SNAPSHOT.jar"]