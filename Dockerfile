# Stage 1: Build the ReactJS frontend
FROM node:14.17.0 as frontend
WORKDIR /app
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ ./
RUN npm run build

# Stage 2: Build the Java/Spring Boot backend
FROM adoptopenjdk:17.0.2_8-jdk-hotspot as backend
WORKDIR /app
COPY backend/ ./
RUN ./mvnw package

# Stage 3: Create the final image
FROM adoptopenjdk:17.0.2_8-jdk-hotspot
WORKDIR /app
COPY --from=frontend /app/build ./frontend
COPY --from=backend /app/target/*.jar ./backend.jar

# Install MySQL client
RUN apt-get update && apt-get install -y mysql-client

# Set environment variables for MySQL connection
ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_NAME=TradingPlatform
ENV DB_USERNAME=root
ENV DB_PASSWORD=TestPassword

CMD ["java", "-jar", "backend.jar"]
