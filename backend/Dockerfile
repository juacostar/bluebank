FROM openjdk:14-jdk-alpine
ADD build/libs/*jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]