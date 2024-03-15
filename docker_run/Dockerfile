# First stage: Gradle build for the existing project
FROM gradle:jdk21 AS gradle-build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Third stage: Set up the runtime environment for the Gradle project
FROM openjdk:21 AS gradle-app
EXPOSE 9090
COPY --from=gradle-build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]
