FROM openjdk:8-jdk-alpine

RUN apk add --no-cache curl bash zip vim tree \
    && curl -s "https://get.sdkman.io" | bash 

COPY sdk_install.sh /tmp

RUN chmod a+x /tmp/sdk_install.sh && /tmp/sdk_install.sh
