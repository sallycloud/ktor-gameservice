FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY build/libs/ktor-gameservice-all.jar ktor-gameservice-all.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ktor-gameservice-all.jar"]