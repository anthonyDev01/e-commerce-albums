# Etapa 1: Build da aplicação
FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY . .

# Executa o build, ignorando os testes
RUN mvn clean package -Dmaven.test.skip=true
RUN ls target/

# Etapa 2: Criar a imagem final
FROM openjdk:17-jdk-alpine AS final

WORKDIR /app

# Copia o JAR gerado da etapa build (corrigido de builder para build)
COPY --from=build /app/target/*.jar /app/app.jar

# Configurações do banco de dados
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://database:5432/postgres
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

# Expõe a porta 8080
EXPOSE 8080

# Executa o JAR gerado
CMD ["java", "-jar", "app.jar"]
