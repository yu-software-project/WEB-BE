# Use the official OpenJDK image from the Docker Hub
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Add the application's jar to the container
ARG JAR_FILE=build/libs/challengeSemster.jar
COPY ${JAR_FILE} app.jar

# Run the jar file
# Run the jar file with the docker profile
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]