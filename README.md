# Spring Boot REST API Project

This is a simple Spring Boot REST API application (version 3.5.7) with example controllers.

## Features

- Spring Boot 3.5.7
- RESTful API endpoints
- Simple controllers for demonstration
- Gradle build system

## Getting Started

### Prerequisites

- Java 17 or higher
- Gradle

### Running the Application

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`

## API Endpoints

### Hello Controller

- `GET /api/hello` - Returns a greeting message
- `GET /api/hello/{name}` - Returns a personalized greeting

### User Controller

- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create a new user

### Product Controller

- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID

## Project Structure

```
src/main/java/com/example/springrestapi/
├── controller/
│   ├── HelloController.java
│   ├── UserController.java
│   └── ProductController.java
├── model/
│   ├── User.java
│   └── Product.java
├── SpringRestApiApplication.java
```

## License

MIT License
