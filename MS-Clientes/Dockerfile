
FROM openjdk:17-jdk-slim
EXPOSE 7085
WORKDIR /app
# Copia el JAR desde la ubicación correcta
COPY out/artifacts/MS_Clientes_jar/MS-Clientes.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]