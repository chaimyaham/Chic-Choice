FROM openjdk:17-oracle
WORKDIR /app
COPY target/*.jar /app/MeteoService.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app/MeteoService.jar"]