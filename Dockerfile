FROM openjdk:17-alpine
LABEL authors="Rodolfo"
WORKDIR /app

COPY target/to-do-0.0.1-SNAPSHOT.jar /app/to-do-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar", "to-do-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081





