FROM openjdk:17-oracle
WORKDIR /app
COPY target/*.jar /app/Discovery.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/Discovery.jar"]