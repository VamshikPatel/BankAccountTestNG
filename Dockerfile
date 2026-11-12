# ==========================================
# STAGE 1: BUILD
# Maven + JDK 17
# ==========================================
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven configuration and TestNG suite
COPY pom.xml .
COPY testng.xml .

# Copy source and test code
COPY src ./src

# Download dependencies
RUN mvn dependency:go-offline -B

# Compile main and TestNG test classes
RUN mvn test-compile

# ==========================================
# STAGE 2: RUNTIME
# Maven + JDK 17
# ==========================================
FROM maven:3.9.9-eclipse-temurin-17
WORKDIR /app

# Copy project files from Stage 1
COPY --from=build /app /app

# Execute TestNG tests
CMD ["mvn", "test"]
