FROM openjdk:17-oracle
WORKDIR /app
COPY target/*.jar /app/VetementService.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/app/VetementService.jar"]