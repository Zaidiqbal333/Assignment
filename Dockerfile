# Use an official OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY out/artifacts/SnappymobProgrammingChallenge_jar/SnappymobProgrammingChallenge.jar /app/SnappymobProgrammingChallenge.jar
COPY out/artifacts/SnappymobProgrammingChallenge_jar/Resources /app/Resources

# Expose the port your application will run on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "SnappymobProgrammingChallenge.jar"]