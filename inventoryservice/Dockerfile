FROM eclipse-temurin:21
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} inventory-service.jar
ENTRYPOINT [ "java", "-jar", "inventory-service.jar" ]