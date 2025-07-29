<<<<<<< HEAD
# Investment Portfolio Application

This project is a Spring Boot application designed to manage an investment portfolio. It allows users to create, retrieve, update, and delete investment records.

## Technologies Used

- Java
- Spring Boot
- PostgreSQL
- Docker

## Project Structure

```
investment-portfolio-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── portfolio
│   │   │               ├── PortfolioApplication.java
│   │   │               ├── controller
│   │   │               │   └── InvestmentController.java
│   │   │               ├── model
│   │   │               │   └── Investment.java
│   │   │               └── repository
│   │   │                   └── InvestmentRepository.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── schema.sql
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── portfolio
│                       └── PortfolioApplicationTests.java
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

## Setup Instructions

1. **Clone the repository:**
   ```
   git clone <repository-url>
   cd investment-portfolio-app
   ```

2. **Build the project:**
   ```
   mvn clean install
   ```

3. **Run the application:**
   You can run the application using Docker:
   ```
   docker-compose up
   ```

4. **Access the application:**
   The application will be available at `http://localhost:8080`.

## Usage

- Use the REST API endpoints provided by the `InvestmentController` to manage your investments.
- The application connects to a PostgreSQL database, which can be configured in the `application.properties` file.

## License

This project is licensed under the MIT License.
=======
# Carteira_Web-projeto
>>>>>>> 1c0d2953340501e45b4a7da79fb328b518205f2d
