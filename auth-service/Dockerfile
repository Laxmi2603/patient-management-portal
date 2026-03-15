# Stage 1: Build the application inside a temporary container
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application and skip tests to ensure a fast build
RUN mvn clean package -DskipTests

# Stage 2: Create the final lightweight production image
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy the JAR from the build stage to this stage
COPY --from=build ./app/target/auth-service-0.0.1-SNAPSHOT.jar ./app.jar

# Run the application
EXPOSE 4005
ENTRYPOINT ["java", "-jar", "app.jar"]