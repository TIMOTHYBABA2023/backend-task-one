# Use an official OpenJDK runtime as a parent image
FROM openjdk:17
# Copy the executable JAR into the container
#ADD target/stageOneTask-0.0.1-SNAPSHOT.jar app1.jar
ADD stageOneTask/target/stageOneTask-0.0.1-SNAPSHOT.jar app1.jar
# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app1.jar"]
