FROM openjdk:17-oracle
WORKDIR /app
COPY target/*.jar /app/Planification.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "/app/Planification.jar"]