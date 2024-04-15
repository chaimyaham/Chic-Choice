FROM openjdk:17-oracle
WORKDIR /app
COPY target/*.jar /app/UtilisateurService.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "/app/UtilisateurService.jar"]