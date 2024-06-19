FROM eclipse-temurin:18-jdk-alpine as builder

# Set the working directory in the container
WORKDIR /app

# Copy the build files to the container
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Add execute permissions to mvnw
RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

# Start a new stage for running the application
FROM eclipse-temurin:18-jdk-alpine

WORKDIR /app
EXPOSE 8080
COPY --from=builder  /target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]