#!/bin/bash

# Task Manager Backend Startup Script

echo "Starting Task Manager Backend..."

# Check if Redis is running
echo "Checking Redis connection..."
if ! nc -z localhost 6379; then
    echo "Redis is not running. Please start Redis first:"
    echo "cd .. && docker-compose up -d redis"
    exit 1
fi

echo "Redis is running. Starting Spring Boot application..."

# Run the Spring Boot application
./mvnw spring-boot:run
