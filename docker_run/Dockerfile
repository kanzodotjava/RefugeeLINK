# First stage: Gradle build for the existing project
FROM gradle:jdk21 AS gradle-build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Second stage: Maven build for the new project
# Assuming the new project is in a sub-directory named `maven-project`
FROM maven:3.8.4-openjdk-17 AS maven-build
COPY --chown=maven:maven ./maven-project /usr/src/maven-project
WORKDIR /usr/src/maven-project
RUN mvn package

# Third stage: Set up the runtime environment for the Gradle project
FROM openjdk:21 AS gradle-app
EXPOSE 9090
COPY --from=gradle-build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]

# Fourth stage: Set up the runtime environment for the Maven project
FROM openjdk:17 AS maven-app
EXPOSE 3000
COPY --from=maven-build /usr/src/maven-project/target/*.jar /app/ms-chat-socket.jar
ENTRYPOINT ["java", "-jar", "/app/ms-chat-socket.jar"]
