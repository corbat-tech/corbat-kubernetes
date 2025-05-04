# Stage 1: Build (optional if you use multi-stage, skip if you build locally)
#FROM maven:3.9-eclipse-temurin-21 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre

# Set a working directory
WORKDIR /app

# Copy the jar file
COPY target/corbat-kubernetes-app.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
