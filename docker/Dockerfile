FROM amd64/eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar cda-backend.jar
EXPOSE 8080
CMD ["java", "-jar", "cda-backend.jar"]