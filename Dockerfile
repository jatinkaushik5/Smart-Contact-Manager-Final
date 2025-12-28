

FROM amazoncorretto:21-alpine-jdk
COPY target/Smart-Contact-Manager-Final-0.0.1-SNAPSHOT.jar Smart-Contact-Manager-Final-0.0.1-SNAPSHOT.jar
# Copy helper scripts and env into the container root
COPY wait-for-it.sh /wait-for-it.sh
COPY .env .env
RUN apk add --no-cache bash
RUN chmod +x /wait-for-it.sh
EXPOSE 8080
# Use the wait script to ensure MySQL is reachable before starting the app
ENTRYPOINT ["/wait-for-it.sh","mysql:3306","--","java","-jar","/Smart-Contact-Manager-Final-0.0.1-SNAPSHOT.jar"]