# Use the official Gradle image as the base image for the build stage
FROM gradle:7.3.3-jdk11 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle configuration files into the container
COPY --chown=gradle:gradle build.gradle settings.gradle /app/

# Copy the Gradle wrapper files
COPY --chown=gradle:gradle gradlew gradlew.bat /app/
COPY --chown=gradle:gradle gradle /app/gradle

# Copy the project source code
COPY --chown=gradle:gradle src /app/src

# Build the application
RUN ./gradlew build

# Use OpenJDK for running the application
FROM openjdk:11-jre-slim

# Copy the built application from the build stage
COPY --from=build /app/build/libs/*.jar /app/application.jar

# Command to run the application
CMD ["java", "-jar", "/app/application.jar"]
