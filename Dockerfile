

FROM amazoncorretto:21-alpine-jdk
COPY target/Smart-Contact-Manager-Final-0.0.1-SNAPSHOT.jar Smart-Contact-Manager-Final-0.0.1-SNAPSHOT.jar
# Copy .env file into the container root
COPY .env .env
EXPOSE 8080
ENTRYPOINT ["java","-jar","/Smart-Contact-Manager-Final-0.0.1-SNAPSHOT.jar"]