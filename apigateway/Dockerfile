FROM eclipse-temurin:21
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} api-gateway.jar
ENTRYPOINT [ "java", "-jar", "api-gateway.jar" ]