# Usar la imagen base de OpenJDK 21
FROM amazoncorretto:21-alpine3.19
# Crear un volumen temporal
VOLUME /tmp
# Copiar el archivo JAR de la aplicación en el contenedor
COPY target/backapp-0.0.1-SNAPSHOT.jar app.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
