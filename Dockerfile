# ===========================
# 1) STAGE DE BUILD
# ===========================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom.xml e baixa dependências (cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o resto do projeto e gera o JAR
COPY . .
RUN mvn clean package -DskipTests

# ===========================
# 2) STAGE DE RUNTIME
# ===========================
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR do estágio de build
COPY --from=build /app/target/*.jar app.jar

# Porta da aplicação
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
