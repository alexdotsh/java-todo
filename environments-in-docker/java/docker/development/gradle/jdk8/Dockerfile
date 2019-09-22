FROM openjdk:8-jdk-alpine

ENV HOME="/root"
ENV GRADLE_VERSION=5.6.2
ENV GRADLE_USER_HOME="$HOME/.gradle"

RUN apk add --no-cache \
        curl \
        bash \
        zip \
        vim \
        tree \
        && curl -s "https://get.sdkman.io" | bash 

COPY run_scripts/sdk_install.sh /tmp

RUN chmod a+x /tmp/sdk_install.sh && /tmp/sdk_install.sh

WORKDIR /home/app
