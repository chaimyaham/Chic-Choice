FROM maven:3.8-openjdk-17 AS build

WORKDIR /app

COPY . .
RUN chmod +x mvnw
RUN mvn clean package

# Étape d'exécution
FROM openjdk:17-jre-slim

WORKDIR /app

COPY --from=build /app/target/Friend-service-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "Friend-service-0.0.1-SNAPSHOT.jar"]