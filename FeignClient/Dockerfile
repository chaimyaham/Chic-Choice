FROM openjdk:17-oracle
WORKDIR /app
COPY target/*.jar /app/FeignClient.jar
ENTRYPOINT ["java", "-jar", "/app/FeignClient.jar"]