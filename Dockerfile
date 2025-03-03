FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/tasktracker-0.0.1-SNAPSHOT.jar /app/tasktracker.jar
EXPOSE 8080
CMD ["java", "-jar", "tasktracker.jar"]