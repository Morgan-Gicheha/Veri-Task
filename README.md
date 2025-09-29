# 🚀 Task Manager Application

A modern, full-stack task management application built with **Spring Boot** (Backend) and **Angular** (Frontend), using **Redis** for data persistence and **Docker** for containerization.

![Status](https://img.shields.io/badge/Status-Production%20Ready-brightgreen)
![Backend](https://img.shields.io/badge/Backend-Spring%20Boot%203.1.1-green)
![Frontend](https://img.shields.io/badge/Frontend-Angular%2019-red)
![Database](https://img.shields.io/badge/Database-Redis%207-red)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Angular UI    │────│  Spring Boot    │────│     Redis       │
│   (Port 4200)   │    │   (Port 8080)   │    │   (Port 6379)   │
│                 │    │                 │    │                 │
│ • Material UI   │    │ • JWT Auth      │    │ • Data Store    │
│ • Reactive Forms│    │ • REST API      │    │ • Persistence   │
│ • Route Guards  │    │ • Security      │    │ • Docker Volume │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## ✨ Features

### 🔐 Authentication & Security
- **JWT-based authentication** with secure token management
- **Spring Security** integration with custom filters
- **Route guards** and HTTP interceptors
- **CORS configuration** for cross-origin requests

### 📋 Task Management
- **Complete CRUD operations** for tasks
- **User-specific task isolation** 
- **Task status management** (PENDING, IN_PROGRESS, COMPLETED)
- **Real-time updates** with reactive forms

### 🎨 Modern UI/UX
- **Angular Material** components with confidence-inspiring theme
- **Responsive design** for all screen sizes
- **Form validation** with user-friendly error messages
- **Loading states** and success notifications

### 🐳 DevOps Ready
- **Full Docker containerization** with multi-stage builds
- **Health checks** and monitoring endpoints
- **Volume persistence** for data retention
- **Production-ready** Nginx configuration

## 🚀 Quick Start

### Prerequisites
- **Docker** and **Docker Compose** installed
- **Git** for cloning the repository

### 🏃‍♂️ One-Command Startup

```bash
# Clone and start the entire application
git clone <your-repository-url>
cd task-manager
./start-docker.sh
```

**That's it!** The script will:
- ✅ Build all Docker images
- ✅ Start all services
- ✅ Wait for health checks
- ✅ Display service URLs

### 🌐 Access Points

| Service | URL | Description |
|---------|-----|-------------|
| 🖥️ **Frontend** | http://localhost:4201 | Main application interface |
| 🔧 **Backend API** | http://localhost:8082 | REST API endpoints |
| 📊 **Redis Commander** | http://localhost:8083 | Database management UI |
| 💚 **Health Check** | http://localhost:8082/actuator/health | Service status |

## 📡 API Documentation

### 🔑 Authentication Endpoints
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "your_username",
  "password": "your_password"
}
```

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "your_username", 
  "password": "your_password"
}
```

### 📝 Task Endpoints (Protected)
```http
# Get all tasks
GET /api/tasks
Authorization: Bearer <jwt_token>

# Create task
POST /api/tasks
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "title": "Task Title",
  "description": "Task Description", 
  "status": "PENDING"
}

# Update task
PUT /api/tasks/{id}
Authorization: Bearer <jwt_token>

# Delete task  
DELETE /api/tasks/{id}
Authorization: Bearer <jwt_token>
```

### 📮 Postman Collection
Import the complete API collection: `Backend/Task-Manager-API.postman_collection.json`
- ✅ Pre-configured requests
- ✅ Environment variables
- ✅ Automatic JWT token management
- ✅ Test scripts

## 🛠️ Development Setup

### Backend Development
```bash
cd Backend
mvn spring-boot:run
# Runs on http://localhost:8080 (development)
# Docker runs on http://localhost:8082 (to avoid conflicts)
```

### Frontend Development  
```bash
cd frontend/task-manager-frontend
npm install
ng serve
# Runs on http://localhost:4200 (development)
# Docker runs on http://localhost:4201 (to avoid conflicts)
```

### Database Access
```bash
# Redis CLI access
docker-compose exec redis redis-cli

# View Redis data
docker-compose exec redis redis-cli keys "*"
```

## 📁 Project Structure

```
task-manager/
├── 🗂️ Backend/                     # Spring Boot Application
│   ├── 📁 src/main/java/com/taskmanager/
│   │   ├── 🔧 controller/          # REST Controllers
│   │   ├── 🛡️ security/            # JWT & Security Config
│   │   ├── 📊 model/               # Data Models
│   │   ├── 🗄️ repository/          # Data Access Layer
│   │   └── 📋 dto/                 # Data Transfer Objects
│   ├── 🐳 Dockerfile              # Backend container
│   ├── 📦 pom.xml                 # Maven dependencies
│   └── 📮 Task-Manager-API.postman_collection.json
│
├── 🗂️ frontend/                    # Angular Application  
│   ├── 📁 task-manager-frontend/
│   │   ├── 🎨 src/app/components/  # UI Components
│   │   ├── 🔧 src/app/services/    # HTTP Services
│   │   ├── 🛡️ src/app/guards/      # Route Guards
│   │   └── 🎯 src/app/interceptors/ # HTTP Interceptors
│   ├── 🐳 Dockerfile              # Frontend container
│   └── ⚙️ nginx.conf               # Nginx configuration
│
├── 🐳 docker-compose.yml          # Multi-service orchestration
├── ⚙️ redis.conf                   # Redis configuration  
├── 🚀 start-docker.sh             # Startup script
├── 📝 .gitignore                  # Git ignore rules
└── 📖 README.md                   # This file
```

## 🔧 Technology Stack

### Backend Stack
| Technology | Version | Purpose |
|------------|---------|---------|
| ☕ **Java** | 17 | Programming Language |
| 🍃 **Spring Boot** | 3.1.1 | Application Framework |
| 🔒 **Spring Security** | 6.x | Authentication & Authorization |
| 🗄️ **Spring Data Redis** | 3.x | Data Persistence |
| 🎯 **JWT** | 0.12.3 | Token-based Authentication |
| 🔨 **Maven** | 3.9+ | Build & Dependency Management |

### Frontend Stack  
| Technology | Version | Purpose |
|------------|---------|---------|
| 🅰️ **Angular** | 19 | Frontend Framework |
| 🎨 **Angular Material** | 19 | UI Component Library |
| 📡 **RxJS** | 7.8+ | Reactive Programming |
| 🎯 **TypeScript** | 5.x | Type-safe JavaScript |

### Infrastructure
| Technology | Version | Purpose |
|------------|---------|---------|
| 🗄️ **Redis** | 7-alpine | In-memory Database |
| 🐳 **Docker** | Latest | Containerization |
| 🌐 **Nginx** | Alpine | Web Server |
| 📊 **Spring Actuator** | 3.x | Health Monitoring |

## ⚙️ Configuration

### Environment Variables
```bash
# Redis Configuration
SPRING_DATA_REDIS_HOST=redis
SPRING_DATA_REDIS_PORT=6379

# JWT Configuration  
JWT_SECRET=your-super-secret-key
JWT_EXPIRATION=86400000

# CORS Configuration
CORS_ALLOWED_ORIGINS=http://localhost:4201
```

### Docker Services
```yaml
services:
  redis:        # Database with persistence (port 6380)
  backend:      # Spring Boot API (port 8082)
  frontend:     # Angular + Nginx (port 4201) 
  redis-commander: # Database GUI (port 8083)
```

## 🧪 Testing

### 🔄 Complete Testing Flow
1. **Register** a new user account
2. **Login** to receive JWT token
3. **Create** multiple tasks
4. **Update** task status and details
5. **Delete** completed tasks
6. **Logout** and verify protection

### 🛠️ API Testing with Postman
1. Import collection: `Backend/Task-Manager-API.postman_collection.json`
2. Import environment: `Backend/Task-Manager-Environment.postman_environment.json`
3. Run the complete test suite
4. Verify automatic token management

### ✅ Verified Functionality
- ✅ User registration and login
- ✅ JWT token generation and validation
- ✅ Protected API endpoints
- ✅ Task CRUD operations
- ✅ User-specific data isolation
- ✅ Frontend-backend integration
- ✅ Docker containerization
- ✅ Data persistence with Redis
- ✅ Health monitoring
- ✅ CORS configuration

## 🐛 Troubleshooting

### Common Issues & Solutions

| Issue | Solution |
|-------|----------|
| 🚫 **Port conflicts** | Uses ports 4201, 8082, 6380, 8083 to avoid common conflicts |
| 🐳 **Docker issues** | Run `docker-compose down && docker-compose up --build` |
| 🔑 **JWT expired** | Re-login to get a fresh token |
| 📡 **CORS errors** | Check `CORS_ALLOWED_ORIGINS` environment variable |

### 📋 Health Checks
```bash
# Check all services
docker-compose ps

# Individual service health
curl http://localhost:8082/actuator/health  # Backend
curl http://localhost:4201/                 # Frontend  
docker-compose exec redis redis-cli ping    # Redis
```

### 📊 Viewing Logs
```bash
# All services
docker-compose logs -f

# Specific services
docker-compose logs -f backend
docker-compose logs -f frontend
docker-compose logs -f redis
```

## 🚀 Production Deployment

### Security Checklist
- [ ] Change default JWT secret
- [ ] Use environment-specific configurations
- [ ] Enable HTTPS/TLS
- [ ] Configure proper CORS origins
- [ ] Set up monitoring and logging
- [ ] Regular security updates

### Performance Optimization
- [ ] Redis connection pooling configured
- [ ] Nginx gzip compression enabled
- [ ] Angular production build optimized
- [ ] Docker multi-stage builds used
- [ ] Health checks implemented

## 📊 Application Status

**Current Status**: ✅ **FULLY FUNCTIONAL**

All components are working correctly:
- ✅ Backend API responding on port 8082
- ✅ Frontend UI accessible on port 4201  
- ✅ Redis database operational with persistence (port 6380)
- ✅ Redis Commander available on port 8083
- ✅ JWT authentication working
- ✅ All CRUD operations tested
- ✅ Docker containers healthy
- ✅ Cross-origin requests configured
- ✅ Data persistence verified
- ✅ Port conflicts resolved

## 🤝 Contributing

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

- 📧 **Issues**: Create an issue in this repository
- 📖 **Documentation**: Check this README and API collection
- 💬 **Discussions**: Use GitHub Discussions for questions

---

**Made with ❤️ for efficient task management**