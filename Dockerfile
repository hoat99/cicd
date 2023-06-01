FROM maven:3.6.3-jdk-11-slim AS MAVEN_BUILD
#FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD FOR JAVA 8
ARG SPRING_ACTIVE_PROFILE
MAINTAINER Harrison
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean install
FROM openjdk:11-slim
#FROM openjdk:8-alpine FOR JAVA 8
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/spring-security-hibernate-2.6.7.jar /app/spring-security-hibernate-2.6.7.jar
ENTRYPOINT ["java", "-jar", "spring-security-hibernate-2.6.7.jar"]
