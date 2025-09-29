#!/bin/bash

echo "🚀 Starting Task Manager Application with Docker..."
echo ""

# Build and start all services
echo "📦 Building and starting services..."
docker-compose up --build -d

echo ""
echo "⏳ Waiting for services to be ready..."

# Wait for Redis to be ready
echo "  - Checking Redis..."
while ! docker-compose exec redis redis-cli ping > /dev/null 2>&1; do
    echo "    Redis not ready yet, waiting..."
    sleep 2
done
echo "  ✅ Redis is ready!"

# Wait for Backend to be ready
echo "  - Checking Backend..."
while ! curl -f http://localhost:8082/actuator/health > /dev/null 2>&1; do
    echo "    Backend not ready yet, waiting..."
    sleep 5
done
echo "  ✅ Backend is ready!"

# Wait for Frontend to be ready
echo "  - Checking Frontend..."
while ! curl -f http://localhost:4201/ > /dev/null 2>&1; do
    echo "    Frontend not ready yet, waiting..."
    sleep 3
done
echo "  ✅ Frontend is ready!"

echo ""
echo "🎉 All services are running!"
echo ""
echo "📋 Service URLs:"
echo "  • Frontend App: http://localhost:4201"
echo "  • Backend API: http://localhost:8082"
echo "  • Redis Commander: http://localhost:8083"
echo "  • Health Check: http://localhost:8082/actuator/health"
echo ""
echo "📊 Service Status:"
docker-compose ps
echo ""
echo "📝 To view logs:"
echo "  docker-compose logs -f frontend"
echo "  docker-compose logs -f backend"
echo "  docker-compose logs -f redis"
echo ""
echo "🛑 To stop all services:"
echo "  docker-compose down"
