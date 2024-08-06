FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/server-0.0.1-SNAPSHOT.jar /app/server.jar

EXPOSE 9090

CMD ["java", "-jar", "/app/server.jar"]
