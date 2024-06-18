# Etapa de construcción
FROM maven:3.8.4-openjdk-17-slim AS build

# Copiar el proyecto y compilarlo
WORKDIR /usr/src/activoscz
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre-focal
WORKDIR /usr/activoscz
COPY --from=build /usr/src/activoscz/target/activoscz-0.0.1-SNAPSHOT.jar ./activoscz-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "activoscz-0.0.1-SNAPSHOT.jar"]
