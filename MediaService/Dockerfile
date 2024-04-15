FROM openjdk:17-oracle
WORKDIR /app
COPY target/*.jar /app/Media.jar
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "/app/Media.jar"]