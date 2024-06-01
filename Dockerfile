FROM eclipse-temurin:17-jdk-alpine
RUN mkdir -p "app"
COPY target/*.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","app.jar"]
