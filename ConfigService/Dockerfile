FROM maven:3.8-openjdk-17 AS build

WORKDIR /app

COPY . .
RUN chmod +x mvnw
RUN mvn clean package


FROM openjdk:17-jre-slim

WORKDIR /app

COPY --from=build /app/target/ConfigService-1.0-SNAPSHOT.jar .

CMD ["java", "-jar", "ConfigService-1.0-SNAPSHOT.jar"]