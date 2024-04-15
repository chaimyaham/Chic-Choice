FROM openjdk:17-oracle
WORKDIR /app
COPY target/*.jar /app/Gateway.jar
EXPOSE 3333
ENTRYPOINT ["java", "-jar", "/app/Gateway.jar"]