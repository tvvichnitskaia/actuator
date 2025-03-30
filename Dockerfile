# Stage 1. build packge
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

#Stage 2. copy over and start instance
FROM openjdk:17-jdk

WORKDIR /app

COPY --from=build /build/target/actuator-0.0.1-SNAPSHOT.jar /app/actuator.jar

EXPOSE 8087

CMD ["java", "-jar", "actuator.jar"]
