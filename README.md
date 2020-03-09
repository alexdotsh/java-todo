## Todo app in Spring using Docker

### How to

### Building the Docker images

#### Build environment - Step 1
Docker image is used for building a *Gradle build* environment. After a successful build, a *build* directory will be created in the same project folder with the build results and a final `.jar` file (To be used in the step 2).

Set environment variables
```bash
DOCKER_IMAGE_NAME="jdk8-gradle-environment:0.0.1"; export DOCKER_IMAGE_NAME
PROJECT_DIRECTORY="$PWD"; export PROJECT_DIRECTORY
APP_NAME="jdk8-app:0.0.1"; export APP_NAME
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

#### Deployable environment - Step 2

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
```

Access at:

```bash
localhost:8080
```
