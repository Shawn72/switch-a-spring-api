FROM amazoncorretto:11-alpine-jdk
EXPOSE 8006
ARG JAR_FILE=target/sa-api-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]