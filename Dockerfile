# Imagen base con Java y Gradle
FROM gradle:7.4.1-jdk11

# Directorio de trabajo
WORKDIR /app

# Copiar los archivos de construcción del proyecto Gradle
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY src ./src

# Construir la aplicación Gradle
RUN gradle build --no-daemon

# Puerto expuesto por la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "build/libs/demo-0.1.jar"]



