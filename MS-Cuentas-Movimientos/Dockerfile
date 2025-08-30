
FROM openjdk:17-jdk-slim
EXPOSE 7086
WORKDIR /app
# Copia el JAR desde la ubicación correcta
COPY out/artifacts/MS_Cuentas_Movimientos_jar/MS-Cuentas-Movimientos.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]