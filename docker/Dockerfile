FROM openjdk:13-jdk-alpine

RUN apk add --no-cache \
        curl \
        bash \
        zip \
        vim

VOLUME /tmp

ARG JAR_FILE

COPY ${JAR_FILE} docker/wait-for-it.sh /

RUN chmod +x wait-for-it.sh

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
