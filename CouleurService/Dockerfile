FROM openjdk:17-oracle
WORKDIR /app
COPY target/*.jar /app/Couleur.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "/app/Couleur.jar"]