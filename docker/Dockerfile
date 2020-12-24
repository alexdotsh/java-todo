FROM adoptopenjdk/openjdk11:jdk-11.0.9_11.1-alpine

RUN apk add --no-cache \
        curl \
        bash \
        zip \
        vim

VOLUME /tmp

ARG JAR

COPY ${JAR} /tmp/app.jar
COPY docker/wait-for-it.sh /

RUN chmod +x wait-for-it.sh

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/tmp/app.jar"]
