# Start with a base Alpine image
FROM openjdk:19-alpine

# Install Maven
RUN apk update && apk add maven

WORKDIR /app

# Copy the entire project into the container
COPY . .

# Expose the port
EXPOSE 8082

# Command to run the tests using Maven
CMD ["mvn", "test"]
