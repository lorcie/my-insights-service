FROM maven:3.8-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

EXPOSE 8090


# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/target/insights-mcp-server-0.0.1-SNAPSHOT.jar"]
