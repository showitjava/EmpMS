FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/*.jar Emp.jar

ENTRYPOINT ["java","-jar","/app/Emp.jar"]