FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY . .

RUN mvn clean package -Dmaven.test.skip=true
RUN ls target/

FROM openjdk:17-jdk-alpine AS final

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/app.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://database:5432/postgres
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

EXPOSE 8080


CMD ["java", "-jar", "app.jar"]