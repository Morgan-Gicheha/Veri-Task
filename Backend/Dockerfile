# Use OpenJDK 17 with Maven pre-installed
FROM openjdk:17-jdk-slim

# Install Maven and curl
RUN apt-get update && \
    apt-get install -y maven curl && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy source code
COPY . .

# Build the application as root to avoid permission issues
RUN mvn clean package -DskipTests

# Create non-root user for runtime
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Change ownership of the JAR file
RUN chown appuser:appuser target/*.jar

# Switch to non-root user
USER appuser

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "target/task-manager-backend-0.0.1-SNAPSHOT.jar"]
