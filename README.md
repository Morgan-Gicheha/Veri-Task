# Task Manager Application

A full-stack task management application built with Spring Boot (backend) and Angular (frontend), using Redis as the database with JWT authentication.

## 🚀 Features

### Backend (Spring Boot)
- **JWT Authentication**: Secure user registration and login
- **RESTful API**: Complete CRUD operations for tasks
- **Redis Database**: Fast, in-memory data storage with persistence
- **Spring Security**: Protected endpoints with role-based access
- **Comprehensive Logging**: Track user activities and errors
- **Input Validation**: Robust data validation and error handling

### Frontend (Angular)
- **Modern UI**: Clean, confidence-inspiring design with Angular Material
- **Responsive Design**: Works seamlessly on desktop and mobile
- **Real-time Updates**: Instant UI updates for all task operations
- **Authentication Flow**: Complete login/register with route guards
- **Task Management**: Create, read, update, delete tasks with status tracking
- **User Experience**: Loading indicators, success/error messages

## 🛠 Technology Stack

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.2.0** - Application framework
- **Spring Security** - Authentication and authorization
- **Spring Data Redis** - Database integration
- **JWT (JJWT 0.12.3)** - Token-based authentication
- **Redis** - Primary database
- **Maven** - Dependency management

### Frontend
- **Angular 19** - Frontend framework
- **Angular Material** - UI component library
- **TypeScript** - Programming language
- **RxJS** - Reactive programming
- **SCSS** - Styling

### Infrastructure
- **Docker Compose** - Container orchestration
- **Redis** - Database with volume persistence

## 📋 Prerequisites

Before running the application, ensure you have:

- **Java 17** or higher
- **Node.js 18** or higher
- **npm** or **yarn**
- **Docker** and **Docker Compose**
- **Git** (for cloning)

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd "Veri Assignment"
```

### 2. Start Redis Database
```bash
# Start Redis with Docker Compose
docker-compose up -d redis

# Verify Redis is running
docker-compose ps
```

### 3. Start the Backend
```bash
# Navigate to backend directory
cd Backend

# Run the Spring Boot application
./mvnw spring-boot:run

# Or use the provided script
chmod +x run.sh
./run.sh
```

The backend will start on `http://localhost:8080`

### 4. Start the Frontend
```bash
# Navigate to frontend directory
cd frontend/task-manager-frontend

# Install dependencies
npm install

# Start the development server
ng serve

# Or start with specific host/port
ng serve --host 0.0.0.0 --port 4200
```

The frontend will start on `http://localhost:4200`

## 📖 API Documentation

### Authentication Endpoints

#### Register User
```http
POST /auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123"
}
```

#### Login User
```http
POST /auth/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123"
}
```

### Task Endpoints (Protected)
All task endpoints require JWT token in Authorization header: `Bearer <token>`

#### Get All Tasks
```http
GET /api/tasks
Authorization: Bearer <jwt-token>
```

#### Create Task
```http
POST /api/tasks
Authorization: Bearer <jwt-token>
Content-Type: application/json

{
  "title": "Complete project documentation",
  "description": "Write comprehensive README and API docs",
  "status": "PENDING"
}
```

#### Update Task
```http
PUT /api/tasks/{id}
Authorization: Bearer <jwt-token>
Content-Type: application/json

{
  "title": "Updated task title",
  "description": "Updated description",
  "status": "COMPLETED"
}
```

#### Delete Task
```http
DELETE /api/tasks/{id}
Authorization: Bearer <jwt-token>
```

## 🎨 User Interface

### Login/Register Pages
- Clean, professional design with gradient backgrounds
- Form validation with real-time error messages
- Password visibility toggle
- Responsive layout for all screen sizes

### Dashboard
- **Statistics Cards**: Show pending, completed, and total tasks
- **Kanban-style Layout**: Separate columns for pending and completed tasks
- **Task Cards**: Rich cards with title, description, status, and actions
- **Floating Action Button**: Quick task creation
- **User Menu**: Easy logout functionality

### Task Management
- **Modal Forms**: Clean popup forms for creating/editing tasks
- **Instant Updates**: Real-time UI updates without page refresh
- **Status Toggle**: Quick status changes between PENDING/COMPLETED
- **Confirmation Dialogs**: Safe deletion with user confirmation

## 🔧 Configuration

### Backend Configuration (`application.yml`)
```yaml
server:
  port: 8080

spring:
  data:
    redis:
      host: localhost
      port: 6379
      database: 0

jwt:
  secret: your-secret-key
  expiration: 86400000 # 24 hours

cors:
  allowed-origins: http://localhost:4200
```

### Redis Configuration (`redis.conf`)
- **Persistence**: Enabled with AOF and RDB
- **Memory Policy**: LRU eviction
- **Security**: Protected mode disabled for development

## 🐳 Docker Setup

### Redis with Persistence
```yaml
services:
  redis:
    image: redis:7-alpine
    ports:
      - "6379:6379"
    volumes:
      - redis_data:/data
      - ./redis.conf:/usr/local/etc/redis/redis.conf
    command: redis-server /usr/local/etc/redis/redis.conf
```

### Redis Commander (Optional)
Access Redis data through web interface at `http://localhost:8081`

## 🔐 Security Features

- **JWT Authentication**: Stateless token-based authentication
- **Password Hashing**: BCrypt encryption for user passwords
- **CORS Protection**: Configured for development and production
- **Input Validation**: Server-side validation for all inputs
- **Route Guards**: Frontend protection for authenticated routes
- **HTTP Interceptor**: Automatic token attachment for API calls

## 📱 Responsive Design

The application is fully responsive and works on:
- **Desktop**: Full-featured experience with side-by-side task columns
- **Tablet**: Optimized layout with stacked columns
- **Mobile**: Touch-friendly interface with collapsible navigation

## 🚀 Production Deployment

### Backend
1. Build the application: `./mvnw clean package`
2. Configure production Redis connection
3. Set secure JWT secret and CORS origins
4. Deploy JAR file to your server

### Frontend
1. Build for production: `ng build --configuration production`
2. Deploy `dist/` folder to web server
3. Configure API URL for production backend

## 🧪 Testing

### Backend Testing
```bash
cd Backend
./mvnw test
```

### Frontend Testing
```bash
cd frontend/task-manager-frontend
npm test
```

## 📝 Development Notes

### Code Quality
- **Clean Architecture**: Separation of concerns with services, controllers, and components
- **Error Handling**: Comprehensive error handling with user-friendly messages
- **Logging**: Strategic logging for debugging and monitoring
- **Comments**: Clear, concise comments where necessary
- **Type Safety**: Full TypeScript usage in frontend

### Performance
- **Lazy Loading**: Angular components loaded on demand
- **Caching**: Redis provides fast data access
- **Optimized Builds**: Production builds with minification and tree-shaking

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/new-feature`
3. Commit changes: `git commit -am 'Add new feature'`
4. Push to branch: `git push origin feature/new-feature`
5. Submit a pull request

## 📄 License

This project is created for technical assessment purposes.

## 📞 Support

If you encounter any issues:
1. Check that Redis is running: `docker-compose ps`
2. Verify backend is accessible: `curl http://localhost:8080/auth/register`
3. Check browser console for frontend errors
4. Review application logs for backend issues

---

**Built with ❤️ using Spring Boot, Angular, and Redis**

