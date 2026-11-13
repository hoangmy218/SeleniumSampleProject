# 1. Use an official Maven image with Java pre-installed
FROM maven:3.9.6-eclipse-temurin-17-alpine

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy ONLY the pom.xml first (helps Docker cache dependencies)
COPY pom.xml .

# 4. Download all dependencies offline (Equivalent to 'pip install')
RUN mvn dependency:go-offline -B

# 5. Copy the rest of your test code and project structure
COPY src ./src

# 6. Copy your TestNG XML suite file(s) into the container root
COPY testng-parameters-allure.xml .