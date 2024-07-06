# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build
COPY src /app/src
WORKDIR /app
COPY pom.xml /app
RUN mvn clean install -DskipTests
RUN mvn -lh /app/target

# Stage 2: Run the application
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/stageOneTask.jar stageOneTask.jar
EXPOSE 8080
CMD ["java", "-jar","stageOneTask.jar"]