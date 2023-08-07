# Fase de compilación
FROM maven:3.8.1-openjdk-17-slim as build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

# Fase de ejecución
FROM openjdk:17-slim
COPY --from=build /app/target/inventory-0.0.1-SNAPSHOT.jar /usr/local/lib/my-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/my-app.jar"]
