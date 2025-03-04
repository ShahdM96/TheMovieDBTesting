FROM maven:3.8-openjdk-23-alpine

WORKDIR /app

# Copy the jar file
COPY target/*.jar app.jar

# Expose the port
EXPOSE 8082

# Command to run the tests using Maven
CMD ["mvn", "test"]
