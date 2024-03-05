# Use the official Gradle image to create a build artifact.
# This is a multi-stage build. In the first stage, we build the JAR file.
FROM gradle:jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon 

# In the second stage, we set up the runtime environment
FROM openjdk:21
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]
