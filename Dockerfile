FROM openjdk:17-jdk-alpine AS build

WORKDIR /app

# Copy project files
COPY . .
RUN chmod +x mvnw
# Build the application
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/tasktracker-0.0.1-SNAPSHOT.jar /app/tasktracker.jar

EXPOSE 8080

CMD ["java", "-jar", "tasktracker.jar"]