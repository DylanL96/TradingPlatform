# TradingPlatform

This is a full-stack project built with ReactJS, Spring Boot, MySQL, and Java. The project allows users to perform stock trading operations.

## Prerequisites

Before getting started, ensure that you have the following installed on your machine:

- Docker: https://docs.docker.com/get-docker/
- Node.js: https://nodejs.org (v14 or higher)
- Java Development Kit (JDK): https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
- MySQL Server: https://dev.mysql.com/downloads/mysql

## Getting Started

1. Clone the repository:
   
     ```
     git clone https://github.com/your-username/project.git
     ```

2. Frontend Setup:

- Navigate to the frontend directory:

  ```
  cd project/frontend
  ```

- Install dependencies:

  ```
  npm install
  ```

3. Backend Setup:

- Import the backend project into an IDE such as VSCode.

- Configure the MySQL database:

  - Create a new MySQL database.

  - Update the database configuration in `src/main/resources/application.properties` with your MySQL connection details.

1. Running the Application (Docker):

- Build and run the application using Docker Compose:

  ```
  docker-compose up
  ```

- The ReactJS frontend will be available at `http://localhost:3000`, and the backend API will be available at `http://localhost:8080`.

5. Running the Application (Manual):

- Start the frontend:

  - Inside the `frontend` directory, run:

    ```
    npm start
    ```

  - The ReactJS application will start running on `http://localhost:3000`.

- Start the backend:

  - Build and run the Spring Boot application from your IDE.

  - The backend API will be available at `http://localhost:8080`.

6. Accessing the Application:

- Open your web browser and visit `http://localhost:3000` to access the ReactJS frontend.

- Use the application to perform stock trading operations.

## Additional Configuration

- To customize any other settings, refer to the respective project's documentation:

- ReactJS: https://create-react-app.dev/docs
- Spring Boot: https://spring.io/projects/spring-boot
- MySQL: https://dev.mysql.com/doc

## Docker Deployment

- The project includes a Dockerfile for containerization. You can build and deploy the application using Docker.

- To build a Docker image, run the following command from the project root directory:

  ```
  docker build -t project .
  ```


- To run the Docker image, use the following command:

  ```
  docker run -p 8080:8080 -d project
  ```
- The application will be available at `http://localhost:8080`.