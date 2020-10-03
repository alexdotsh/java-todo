# Todo app in Java using Spring

## Table of Contents
* [Requirements](#requirements)
* [Configuration](#configuration)
* [Configuration with Docker](#configuration-with-docker)
* Optional
  * [Configure OAuth 2.0](#configure-oauth-20)
* [Run it](#run-it)

## Requirements
* [OpenJDK](https://openjdk.java.net/install/) 8 and above
* [Gradle](https://gradle.org/install/) 6.0.0 and above
* [MySQL](https://dev.mysql.com/downloads/installer/) 8 and above
* (Optional)
  * [Docker](https://docs.docker.com/install/) 18.09 and above

## Configuration

Work in progress

MySQL configuration (based on `src/main/resources/application.yml`)
```bash
mysql -u user-with-privilege -p
```

```sql
CREATE DATABASE todos_db;
CREATE USER 'spring'@'localhost' IDENTIFIED BY 'spring';
GRANT ALL PRIVILEGES ON todos_db.* TO 'spring'@'localhost';
FLUSH PRIVILEGES;
QUIT
```

Using MySQL docker container
```bash
docker run --name database \
    -e MYSQL_ROOT_PASSWORD=rootpassword \
    -e MYSQL_PASSWORD=spring \
    -e MYSQL_USER=spring \
    -e MYSQL_DATABASE=todos_db
    -d mysql:8.0.21
```

```bash
git clone https://github.com/alexmirkhaydarov/java-todo.git

cd java-todo

gradle build

java -jar ./build/libs/todo-0.0.1-SNAPSHOT.jar
```

Access at: `http://localhost:8080`

## Configuration with Docker

Building the Docker images

### Build environment - Step 1
Docker image is used for building a *Gradle build* environment. After a successful build, a *build* directory will be created in the same project folder with the build results and a final `.jar` file (To be used in the step 2).

Set environment variables
```bash
DOCKER_IMAGE_NAME="openjdk13-gradle-environment:0.0.1"; export DOCKER_IMAGE_NAME
PROJECT_DIRECTORY="$PWD"; export PROJECT_DIRECTORY
APP_NAME="openjdk13-app:0.0.1"; export APP_NAME
JAR_PATH="build/libs/"; export JAR_PATH
JAR_FILE="todo-0.0.1-SNAPSHOT.jar"; export JAR_FILE
JAR=$JAR_PATH$JAR_FILE; export JAR
```

Build Gradle Docker image
```bash
docker build --rm -t "$DOCKER_IMAGE_NAME" -f ./docker/Dockerfile.build ./docker/.
```

Run Gradle build
```bash
docker volume create --name gradle-cache
docker run --rm -v gradle-cache:/home/gradle/.gradle -v "$PROJECT_DIRECTORY":/home/gradle "$DOCKER_IMAGE_NAME" gradle build
```

## Configure OAuth 2.0

(Optional)

application.yml
```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          facebook:
            client-id: facebook-client-id
            client-secret: facebook-client-secret
# ...
```
Simply use the OAuth 2.0 credentials you created with Facebook, replacing `facebook-client-id` with the client id and `facebook-client-secret` with the client secret.

## Run it

Running the Docker images

### Deployable environment - Step 2

a)
This Docker image is used to copy the `libs/*-0.0.1-SNAPSHOT.jar` file (from step 1) into the image and be able launch/run it from within the container.

Build image with `*.jar` file (From step 1)
```bash
docker build --rm -t "$APP_NAME" --build-arg JAR="$JAR" -f ./docker/Dockerfile .
```

Run container with `*.jar` file (From step 1) mounted
```bash
docker run --rm -p 8080:8080 -v "$JAR:/tmp/app.jar" $APP_NAME
```

b)
Hot reload with docker-compose
```bash
docker-compose build && docker-compose up

docker run --rm -v gradle-cache:/home/gradle/.gradle -v "$PROJECT_DIRECTORY":/home/gradle "$DOCKER_IMAGE_NAME" gradle build && docker-compose restart app

rm -rf build; docker run --rm -v gradle-cache:/home/gradle/.gradle -v "$PROJECT_DIRECTORY":/home/gradle "$DOCKER_IMAGE_NAME" gradle build && docker-compose restart app
```

Access at: `http://localhost:8080`

## License

[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](LICENSE)
