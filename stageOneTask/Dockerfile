# Use an official Maven image to build the app
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn package -DskipTests

# Use an official OpenJDK image to run the app
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar stageOneTask.jar

# Expose the port on which the app will run
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "stageOneTask.jar"]
